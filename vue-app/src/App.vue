<template>
    <div id="xp-container">
        <left-bar logo="img/logo.png" url="/"/>
        <div class="xp-rightbar">
            <top-bar/>
        </div>
    </div>
</template>

<script>
import LeftBar from "@/components/leftBar/LeftBar.vue";
import TopBar from "@/components/topBar/TopBar.vue";

export default {
    components: {
        LeftBar,
        TopBar
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
    left: -250px;
    transition: all 0.3s ease;
}

.xp-toggle-menu .xp-rightbar {
    margin-left: 0;
}

.xp-toggle-menu .xp-topbar {
    left: 0;
}

.xp-toggle-menu .xp-leftbar {
    position: fixed;
    left: 0;
    z-index: 9999;
}*/


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



</style>
