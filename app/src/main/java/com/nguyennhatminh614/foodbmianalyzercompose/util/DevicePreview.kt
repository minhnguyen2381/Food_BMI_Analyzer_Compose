package com.nguyennhatminh614.foodbmianalyzercompose.util

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * Custom annotation để preview UI trên nhiều thiết bị và chế độ sáng/tối.
 */
// Phone
@Preview(name = "1. Phone - Light", device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Preview(name = "2. Phone - Dark", device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true, showBackground = true)

// Foldable
@Preview(name = "3. Foldable - Light", device = "spec:width=673dp,height=841dp", showSystemUi = true, showBackground = true)
@Preview(name = "4. Foldable - Dark", device = "spec:width=673dp,height=841dp", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true, showBackground = true)

// Tablet
@Preview(name = "5. Tablet - Light", device = "spec:width=1280dp,height=800dp,dpi=240", showSystemUi = true, showBackground = true)
@Preview(name = "6. Tablet - Dark", device = "spec:width=1280dp,height=800dp,dpi=240", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true, showBackground = true)
annotation class DevicePreview