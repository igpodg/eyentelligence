<template>
    <div id="xp-container">
        <left-bar logo="img/logo.png" url="/"/>
        <div class="xp-rightbar">
            <top-bar/>
            <breadcrumbs title="Test" :hierarchy="hierarchy()"/>
            <div class="xp-contentbar">
                <card header="Header" subtitle="Subtitle">
                    asd
                </card>
                <card-invite header="Invite new members"
                             subtitle="Have anyone missing? Just add them using the form below."
                             :team-names="['SuperTeam', 'Company Inc.', 'Europe Sales', 'Asia Sales']"/>
                <card header="Header" subtitle="Subtitle">
                    <statistics-brief :data="statisticsData()"/>
                </card>
            </div>
        </div>
        <bottom-bar>
            2020 Eyentelligence - All Rights Reserved.
        </bottom-bar>
    </div>
</template>

<script>
import LeftBar from "@/components/leftBar/LeftBar.vue";
import TopBar from "@/components/topBar/TopBar.vue";
import BottomBar from "@/components/BottomBar.vue";
import Breadcrumbs from "@/components/Breadcrumbs.vue";
import Card from "@/components/card/Card.vue";
import CardInvite from "@/components/card/CardInvite.vue";
import StatisticsBrief from "@/components/StatisticsBrief.vue";

export default {
    components: {
        LeftBar,
        TopBar,
        BottomBar,
        Breadcrumbs,
        Card,
        CardInvite,
        StatisticsBrief
    },
    methods: {
        hierarchy: function() {
            return [
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
            ];
        },
        statisticsData: function() {
            return [
                {
                    "left": ["5.3 hrs", "Avg Members Sessions", "+18.68%", "vs in last 7 days"],
                    "right": ["6.4 hrs", "Avg Members Sessions", "+23.54%", "vs in last 7 days"]
                },
                {
                    "left": ["2.1 hrs", "Avg Members Sessions", "+56.21%", "vs in last 7 days"],
                    "right": ["4.9 hrs", "Avg Members Sessions", "+45.34%", "vs in last 7 days"]
                },
                {
                    "left": ["11.2 hrs", "Avg Members Sessions", "+56.43%", "vs in last 7 days"],
                    "right": ["0.4 hrs", "Avg Members Sessions", "+98.32%", "vs in last 7 days"]
                }
            ];
        }
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
