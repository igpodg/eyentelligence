module.exports = {
    pwa: {
        iconPaths: {
            favicon32: "assets/img/icons/favicon-32x32.png",
            favicon16: "assets/img/icons/favicon-16x16.png",
            appleTouchIcon: "assets/img/icons/apple-touch-icon-152x152.png",
            maskIcon: "assets/img/icons/safari-pinned-tab.svg",
            msTileImage: "assets/img/icons/msapplication-icon-144x144.png"
        },
        manifestOptions: {
            icons: [
                {
                    src: "./assets/img/icons/android-chrome-192x192.png",
                    sizes: "192x192",
                    type: "image/png"
                },
                {
                    src: "./assets/img/icons/android-chrome-512x512.png",
                    sizes: "512x512",
                    type: "image/png"
                },
                {
                    src: "./assets/img/icons/android-chrome-maskable-192x192.png",
                    sizes: "192x192",
                    type: "image/png",
                    purpose: "maskable"
                },
                {
                    src: "./assets/img/icons/android-chrome-maskable-512x512.png",
                    sizes: "512x512",
                    type: "image/png",
                    purpose: "maskable"
                }
            ]
        }
    }
};
