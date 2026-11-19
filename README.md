# Oxygen Customizer Notification Style Template

A template for notification style packs for [Oxygen Customizer](https://github.com/DHD2280/Oxygen-Customizer).

A pack is a normal APK: you don't need overlays, a Magisk module or a reboot. Oxygen Customizer reads the pack resources and replaces the SystemUI resources from its Xposed hooks. Install the APK, then pick a style in **Oxygen Customizer → Notification style packs**. SystemUI restarts when you apply a style.

The template contains one style, `ExampleStyle`: a plain card that follows the system theme. Rename it, change it, or add more styles next to it. For a full pack, see [OCNotificationStylesPack](https://github.com/DHD2280/OCNotificationStylesPack), which contains the 23 classic Oxygen Customizer styles.

Before you publish your pack, change `namespace` and `applicationId` in `app/build.gradle.kts`, the `app_name` and `pack_author` strings, and the package of `MainActivity`.

## How a pack works

### 1. Declare each style

Every style is an `activity-alias` with the `it.dhd.oxygencustomizer.notificationstyle` action. The alias label is the style name shown in the app.

```xml
<activity-alias
    android:name=".MyStyle"
    android:exported="true"
    android:label="@string/my_style"
    android:targetActivity=".MainActivity">
    <intent-filter>
        <action android:name="it.dhd.oxygencustomizer.notificationstyle" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity-alias>
```

One APK can hold any number of styles. To show the author name, add an application meta-data entry:

```xml
<meta-data
    android:name="packauthor"
    android:value="Your name" />
```

### 2. Map the resources

Each style needs a `string-array` with the same name as its alias (without the leading dot). Every item has the form `type/target=replacement`:

- `type`: `drawable`, `color`, `bool`, `integer` or `dimen`. A `dimen` item can also be a float (`<item type="dimen" format="float">`).
- `target`: the SystemUI resource to replace. To target a framework resource, prefix it with `android:`, for example `android:dimen/config_dialogCornerRadius=my_radius`.
- `replacement`: the name of a resource of the same type in your pack.

You can also add `preview=<drawable>` to set the drawable used as the preview card in the app. By default the app uses the replacement of `drawable/notification_material_bg`.

```xml
<string-array name="MyStyle" translatable="false">
    <item>drawable/notification_material_bg=my_style_notification_bg</item>
    <item>dimen/notification_corner_radius=my_style_radius</item>
    <item>bool/config_showGroupNotificationBgWhenExpanded=my_style_group_bg</item>
</string-array>
```

### 3. Write the resources

The replacements are normal resources of your APK, so:

- Use `drawable-night` for dark mode variants.
- Reference only your own resources or **public** framework ones (for example `@android:color/system_accent1_600` and `?android:colorControlHighlight`). Private references (`@*android:`) don't build.
- Prefix the resource names with the style name to keep styles apart (`example_…`, `mystyle_…`).

## Notes

- The main notification background is `drawable/notification_material_bg`. On ColorOS / OxygenOS 17 Oxygen Customizer also draws it in place of the stock blurred background and disables the stock tint.
- Values that SystemUI reads from code (for example `getDimensionPixelSize`) are replaced. Values that SystemUI only references from its own layouts or styles can't be replaced.
- Only one style can be active at a time.

## Build

```
./gradlew :app:assembleRelease
```

Sign the APK with your own key before you publish it.
