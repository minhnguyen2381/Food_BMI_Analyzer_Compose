## Jetpack Compose Coding Conventions: Additional Best Practices

Establishing a solid coding convention for Jetpack Compose is crucial for ensuring high performance (avoiding unnecessary recompositions), readability, and project scalability. Below are practical rules and best practices to apply to your team's workflow.

### 1. Modifier Rules

Modifiers are the soul of the Compose UI; misusing them can severely hinder component reusability.

* **Always the first optional parameter:** If your Composable accepts a `modifier`, place it as the first parameter immediately after any required parameters.
* **Always set the default to `Modifier`:** Ensure the default value is an empty `Modifier` (e.g., `modifier: Modifier = Modifier`) to allow parent components to easily override layout, padding, or size.
* **Never pass a mutated Modifier as default:** Avoid setting defaults like `modifier: Modifier = Modifier.padding(16.dp)`. Apply padding at the root element inside the Composable itself, or let the parent component provide it.
* **Order matters:** Establish a strict team rule that Modifiers execute from the outside in. Always review the chaining order (e.g., `padding` -> `background` -> `padding`) carefully to prevent UI rendering bugs.

### 2. State Management & Architecture

Unidirectional Data Flow (UDF) is a core principle in Compose that must be strictly followed.

* **Separate Stateful and Stateless Composables:** Design your Screen-level components to be stateful (handling ViewModels, collecting state, and complex logic) and your UI-level components to be strictly stateless (receiving only raw data and callbacks). This makes calling `@Preview` straightforward without mocking ViewModels.
* **Group callbacks to avoid Parameter Hell:** If a Composable requires 4-5 or more event callbacks (click, swipe, text change), group them into a single data class of lambdas, an interface, or use a unified `onEvent(event: MyUiEvent)` function.
* **Use `derivedStateOf` for derived states:** Wrap states that depend on rapidly changing values (such as using `listState.firstVisibleItemIndex` to show a "Scroll to Top" button) in `derivedStateOf`. Failing to do so will cause the Composable to recompose dozens of times per second while scrolling.

### 3. Performance Optimization

Jetpack Compose is smart at skipping recompositions, but it relies on you writing code correctly to do so.

* **Protect parameter stability:** Compose only skips recomposition if the input parameters are "Stable". Use collections like `List`, `Set`, and `Map` from the `kotlinx.collections.immutable` library instead of standard Kotlin collections.
* **Annotate UI state models:** Always mark Data Classes containing UI data with `@Immutable` or `@Stable` annotations.
* **Always provide a `key` for `LazyColumn` / `LazyRow`:** When rendering lists, explicitly provide the `key` parameter in the `items()` function. This helps Compose uniquely identify items during additions, deletions, or reordering, preventing content rendering errors and lost animations.
* **Avoid heavy computations directly in Composables:** Wrap list filtering, date formatting, and any other complex calculations inside `remember { ... }` (with the appropriate keys) so they are not re-calculated on every recomposition.

### 4. Side Effects Rules

* **No side effects in the Composable body:** Never perform API calls, file I/O operations, logging, or global variable mutations directly within the `@Composable` block.
* **Use the correct Effect Handlers:** Utilize `LaunchedEffect` for suspend functions (like displaying a Snackbar) and `DisposableEffect` for tasks that require cleanup (like registering BroadcastReceivers or lifecycle listeners).

---
**Pro Tip for CI/CD:** Consider integrating **ktlint** or **detekt** along with a Compose-specific ruleset (such as `twitter/compose-rules` or `mrmans0n/compose-rules`) into your pipeline to automate the detection of these convention violations.

### 5. Component Granularity & Separation of Concerns

Avoid creating monolithic "God" Composables by stuffing an entire screen's UI into a single function.

* **Break down the UI:** Divide complex screens into smaller, highly focused, and reusable sub-components (e.g., `HeaderSection`, `ProductListItem`, `BottomActionGroup`).
* **Rule of thumb for extraction:** If a Composable function exceeds 100-150 lines of code or manages multiple visually and logically unrelated parts of the UI, extract those parts into their own private or public Composable functions.
* **Keep it flat:** Avoid deep nesting of Compose layouts (like `Column` inside `Row` inside `Box` endlessly). Extracting smaller UI pieces helps maintain a flatter, more readable UI hierarchy.

### 6. Mandatory `@Preview` Usage

Previews are essential for independent component development, visual verification, and acting as living documentation for your UI components.

* **Always write Previews for Stateless components:** Every UI-level (stateless) Composable must be accompanied by at least one `@Preview` function.
* **Preview multiple states:** Do not just preview the "happy path" (success state). You must include previews for different scenarios such as Loading state, Error state, Empty state, and long-text edge cases.
* **Support Day/Night modes:** Where applicable, use `@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)` alongside the default light theme to ensure the component looks good in both modes.
* **Use `@PreviewParameter` for complex data:** Instead of hardcoding large mock objects directly inside the Preview function, use `PreviewParameterProvider` to supply realistic mock data cleanly.