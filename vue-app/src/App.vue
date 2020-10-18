<template>
    <div id="xp-container" v-if="!isSecondaryRoute()">
        <left-bar :logo="this.$logoPath" url="/"/>
        <div class="xp-rightbar">
            <top-bar/>
            <breadcrumbs :hierarchy="hierarchy"
                         :editable="isDashboard()"
                         ref="crumb" :key="crumbKey"/>
            <div class="xp-contentbar">
                <router-view/>
            </div>
        </div>
        <bottom-bar>
            2020 Eyentelligence - All Rights Reserved.
        </bottom-bar>
    </div>
    <router-view v-else/>
</template>

<script>
import LeftBar from "@/components/leftBar/LeftBar.vue";
import TopBar from "@/components/topBar/TopBar.vue";
import BottomBar from "@/components/BottomBar.vue";
import Breadcrumbs from "@/components/Breadcrumbs.vue";

export default {
    components: {
        LeftBar,
        TopBar,
        BottomBar,
        Breadcrumbs
    },
    data: function() {
        return {
            crumbKey: false,
            hierarchy: {
                title: "Test",
                path: [
                    {
                        title: "Testing1",
                        url: "link1"
                    },
                    {
                        title: "Testing2",
                        url: "link2"
                    },
                    {
                        title: "Testing3",
                        url: "link3"
                    }
                ]
            }
        }
    },
    methods: {
        isSecondaryRoute: function() {
            return ["Login", "Invited"].includes(this.$route.name);
        },
        isDashboard: function() {
            return ["Dashboard"].includes(this.$route.name);
        },
        switchBodyColor: function() {
            let secondary = "secondary";
            if (this.isSecondaryRoute())
                document.body.classList.add(secondary);
            else
                document.body.classList.remove(secondary);
        }
    },
    mounted: function() {
        this.switchBodyColor();
    },
    watch: {
        $route: function() {
            this.switchBodyColor();
        }
    },
    beforeUpdate: function() {
        //this.$refs.crumb.$forceUpdate();
        this.crumbKey = !this.crumbKey;
    }
}
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css?family=Comfortaa:300,400,700");
html {
    position: relative;
    min-height: 100%;
}

body {
    font-family: "Comfortaa", cursive;
    margin: 0;
    background: #F0F4F9;
    font-size: 15px;
    font-weight: 400;
    color: #8A98AC;
    line-height: 1.5;
}

p {
    line-height: 1.3;
    margin-bottom: 10px;
}

* {
    outline: none !important;
}

a {
    color: #4c7cf3;
}
a:hover {
    color: #346bf1;
    outline: 0;
    text-decoration: none;
}
a:active {
    color: #346bf1;
    outline: 0;
    text-decoration: none;
}
a:focus {
    color: #346bf1;
    outline: 0;
    text-decoration: none;
}

@media (min-width: 768px) and (max-width: 991px) {
    body {
        overflow-x: hidden;
    }
}

@media (max-width: 767px) {
    body {
        overflow-x: hidden;
    }
}



p {
    line-height: 1.3;
    margin-bottom: 10px;
}


.xp-rightbar {
    margin-left: 250px;
    overflow: hidden;
    min-height: 500px;
    transition: all 0.3s ease;
}









/*.xp-toggle-menu .xp-footerbar {
    left: 0;
}

.xp-toggle-menu .xp-leftbar {
    position: fixed;
    left: 0;
    z-index: 9999;
}*/




.xp-toggle-menu .xp-rightbar {
    margin-left: 0;
}






.xp-toggle-menu .xp-topbar {
    left: 0;
}




.xp-toggle-menu .xp-leftbar {
    position: fixed;
    left: -250px;
    transition: all 0.3s ease;
}

@media (max-width: 767px) {
    .xp-toggle-menu .xp-leftbar {
        position: fixed;
        left: 0;
        z-index: 9999;
    }
}





@media print {
    .xp-rightbar {
        margin-left: 0;
    }
}

@media (max-width: 768px) {
    .xp-rightbar {
        margin-left: 0;
    }
}

@media (max-width: 767px) {
    .xp-rightbar {
        margin-left: 0 !important;
    }
}



.xp-contentbar {
    padding: 30px;
    margin-bottom: 30px;
}


</style>
