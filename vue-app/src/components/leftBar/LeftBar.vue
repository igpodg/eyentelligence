<template>
    <div class="xp-leftbar">
        <div class="xp-logobar text-center">
            <router-link :to="url" class="xp-logo">
                <img :src="logo" class="img-fluid" alt="logo">
            </router-link>
        </div>

        <div class="xp-navigationbar">
            <ul class="xp-vertical-menu">
                <left-bar-heading name="Main"/>
                <left-bar-static icon="doc" name="Overview" url=""/>
                <left-bar-static icon="list" name="Statistics" url=""/>

                <left-bar-heading name="Teams / Organizations"/>
                <left-bar-dropdown v-for="th in tempHeadings" :key="th"
                                   icon="briefcase" :name="th" url="">
                    <left-bar-static-mini name="Home" url=""/>
                    <left-bar-static-mini name="My Dashboard" url="/dashboard"/>
                    <left-bar-static-mini name="My Dashboard #2" url=""/>
                    <hr>
                    <left-bar-static-mini name="Dashboards..." url=""/>
                    <left-bar-static-mini name="Members..." url=""/>
                    <left-bar-static-modal name="Leave Team" type="danger"/>
                </left-bar-dropdown>

                <left-bar-heading name="Extras"/>
                <left-bar-static icon="plus" name="Join a Team" url=""/>
                <left-bar-static icon="people" name="Create a New Team" url=""/>
            </ul>
        </div>
    </div>
</template>

<script>
import LeftBarHeading from "@/components/leftBar/LeftBarHeading.vue";
import LeftBarStatic from "@/components/leftBar/LeftBarStatic.vue";
import LeftBarStaticMini from "@/components/leftBar/LeftBarStaticMini.vue";
import LeftBarStaticModal from "@/components/leftBar/LeftBarStaticModal.vue";
import LeftBarDropdown from "@/components/leftBar/LeftBarDropdown.vue";

export default {
    name: "LeftBar",
    components: {
        LeftBarHeading,
        LeftBarStatic,
        LeftBarStaticMini,
        LeftBarStaticModal,
        LeftBarDropdown
    },
    props: {
        logo: String,
        url: String
    },
    data: function() {
        return {
            tempHeadings: [
                "SuperTeam",
                "Company Inc.",
                "Europe Sales",
                "Asia Sales"
            ]
        };
    }
}
</script>

<style lang="scss">
$m-size: 768px;
$m-size-1: $m-size - 1px; //767 px

$color-black: #000000;
$color-white: #ffffff;

$color-logo: #313131;

.xp-logo {
    color: $color-logo !important;
    font-size: 20px;
    font-weight: 700;
    letter-spacing: 0.03em;
    line-height: 100px;
    text-transform: uppercase;
}

.xp-toggle-menu {
    .xp-leftbar {
        position: fixed;
        left: -250px;
        transition: all 0.3s ease;

        @media (max-width: $m-size-1) {
            position: fixed;
            left: 0;
            z-index: 9999;
        }
    }
}

$color-leftbar-shadow: #c8c8c8;

.xp-leftbar {
    width: 250px;
    height: 100%;
    position: fixed;
    background-color: $color-white;
    box-shadow: 0 0 30px 0 rgba($color-leftbar-shadow, 0.2);
    z-index: 9;
    transition: all 0.3s ease;

    overflow-y: auto;
    scrollbar-width: none; /* Firefox */
    -ms-overflow-style: none;  /* IE 10+ */
    &::-webkit-scrollbar {
        width: 0;
        background: transparent; /* Chrome/Safari/Webkit */
    }
}










$gradient-selected-item-begin: #4c7cf3;
$gradient-selected-item-end: #4cc6f3;

%selected-item {
    color: $color-white;
    background-image: linear-gradient(58deg,
        $gradient-selected-item-begin 0,
        $gradient-selected-item-end 100%);
    border-radius: 50px 0 0 50px;
    box-shadow: 0 0.5rem 1rem rgba($color-black, 0.15);
}


.xp-vertical-menu {
    list-style: none;
    margin: 0;
    padding: 0;

    >li {
        position: relative;
        margin: 5px 0;
        padding: 0 0 0 15px;

        >a {
            padding: 10px 20px;
            display: block;
            color: #8a98ac;

            >i {
                display: inline-block;
                width: 30px;
                font-size: 16px;
                vertical-align: middle;
            }

            >span {
                vertical-align: middle;
            }
        }

        &:hover {
            >a {
                @extend %selected-item;
            }
        }

        &.active > a {
            @extend %selected-item;
        }
    }

    li {
        &.xp-vertical-header {
            padding: 14px 25px;
            font-size: 12px;
        }

        &.active > a > .icon-arrow-right {
            transform: rotate(-270deg);
        }
    }

    li > a > .icon-arrow-right {
        width: auto;
        height: auto;
        padding: 0;
        font-size: 10px;
        font-weight: 700;
        line-height: 24px;
    }

    a {
        color: #8a98ac;
        text-decoration: none;
    }

    .xp-vertical-submenu {
        display: none;
        list-style: none;
        padding-left: 5px;
        margin: 0 1px;
        background: rgba(#0d0d0d, 0);

        .xp-vertical-submenu {
            padding-left: 20px;
        }

        > li > a {
            padding: 8px 25px 8px 45px;
            display: block;
            font-size: 14px;
            color: #8a98ac;

            >.icon-arrow-right {
                width: auto;
            }

            &:hover {
                color: #4c7cf3;
            }
        }

        >hr {
            margin-left: 25px !important;
        }
    }
}



.text-danger {
    color: #ff4b5b !important;

    // links
    @at-root a#{&} {
        &:focus {
            color: #ff3244 !important;
        }

        &:hover {
            color: #ff3244 !important;
        }
    }
}

.pull-right {
    float: right;
}

.xp-leftbar {
    @media print {
        display: none;
    }

    @media (max-width: 767px) {
        position: fixed;
        left: -250px;
    }
}






</style>
