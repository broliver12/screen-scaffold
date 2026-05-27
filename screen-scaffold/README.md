## ScreenContentScaffold

`screen-scaffold` is a small Compose SDK module that provides
`ScreenContentScaffold`, a reusable screen container for apps that need optional
top content, sticky bottom content, scrollable body content, and sensible system
bar/IME padding behavior.

The scaffold supports:

- optional headers
- optional footers
- transparent sticky footers, with content allowed behind the footer
- regular `Column` content
- `LazyColumn` content
- custom content padding
- screens that use a bottom tab/navigation bar instead of a scaffold footer
- optional Material3 top app bar scroll behavior wiring

## Snapshot Tests

Snapshot coverage is contained in this module under:

`src/screenshotTest/kotlin/com/ostraszynski/screen_scaffold/ScreenContentScaffoldSnapshotTest.kt`

The tests use Android's Compose Preview Screenshot Testing plugin. Each snapshot
case is a Compose preview marked with `@PreviewTest`, so the test surface is
generated from previews rather than instrumentation UI tests.

Current cases cover light and dark snapshots for:

- no header and no footer
- header only
- footer only
- header and footer
- transparent footer
- lazy column content
- tab navigation shown with no scaffold footer

Reference images live under:

`src/screenshotTestDebug/reference`

## Adding Screenshot Tests

Add new screenshot tests by creating or updating previews in:

`src/screenshotTest/kotlin/com/ostraszynski/screen_scaffold/ScreenContentScaffoldSnapshotTest.kt`

Each generated screenshot test needs both `@PreviewTest` and a Compose preview
annotation, usually `@PreviewLightDark`:

```kotlin
@PreviewTest
@PreviewLightDark
@Composable
private fun ScreenContentScaffoldExampleSnapshot() {
    ScreenContentScaffold(
        content = {
            Text("Example")
        },
    )
}
```

After adding or changing a preview, generate the reference images:

```sh
./gradlew :screen-scaffold:updateDebugScreenshotTest
```

Review the generated PNG files under:

`src/screenshotTestDebug/reference`

Then validate the snapshots before committing:

```sh
./gradlew :screen-scaffold:validateDebugScreenshotTest
```

## Running Tests

Validate the checked-in reference images:

```sh
./gradlew :screen-scaffold:validateDebugScreenshotTest
```

Update reference images after an intentional UI change:

```sh
./gradlew :screen-scaffold:updateDebugScreenshotTest
```

The validation report is generated at:

`build/reports/screenshotTest/preview/debug/index.html`
