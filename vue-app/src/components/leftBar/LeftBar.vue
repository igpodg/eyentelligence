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
                <left-bar-static icon="doc" name="Overview" url="/"/>
                <left-bar-static icon="list" name="Statistics" url="/statistics"/>

                <left-bar-heading name="Teams / Organizations"/>
                <left-bar-dropdown v-for="team in teams" :key="team.id"
                                   icon="briefcase" :name="team.name" url="" :index="team.id">
                    <left-bar-static-mini name="Home" :url="'/team/' + team.id"/>
                    <left-bar-static-mini v-for="dash in dashboards[team.id]" :key="dash.id"
                                          :name="dash.name" :url="'/team/' + team.id + '/dashboard/' + dash.id"/>
                    <hr>
                    <left-bar-static-mini name="Dashboards..." :url="'/team/' + team.id + '/dashboards'"/>
                    <left-bar-static-mini name="Members..." :url="'/team/' + team.id + '/members'"/>
                    <left-bar-static-modal name="Leave Team" color="danger"
                                           target="xpLeaveModal" :team="team"
                                           :owner="team.id % 2 === 0"/><!-- demo purposes -->
                </left-bar-dropdown>

                <left-bar-heading name="Extras"/>
                <left-bar-static icon="plus" name="Join a Team" url="/join"
                                 internal-name="JoinTeam"/>
                <left-bar-static icon="people" name="Create a New Team" url="/create"
                                 internal-name="CreateTeam"/>
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
        url: String,
        teams: Array,
        dashboards: Object
    },
    created: function() {
        this.$eventBus.$emit("update-teams");
        this.$eventBus.$emit("update-user");
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

    @media print {
        display: none;
    }

    @media (max-width: $m-size-1) {
        position: fixed;
        left: -250px;
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

$color-item: #8a98ac;

.xp-vertical-menu {
    list-style: none;
    margin: 0;
    padding: 0;

    a {
        color: $color-item;
        text-decoration: none;
    }

    >li {
        &:hover {
            >a {
                @extend %selected-item;
            }
        }

        &.active > a {
            @extend %selected-item;
        }

        position: relative;
        margin: 5px 0;
        padding: 0 0 0 15px;

        >a {
            padding: 10px 20px;
            display: block;
            color: $color-item;

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
    }
}

$color-text-danger: #ff4b5b;
$color-text-danger-link: #ff3244;

.text-danger {
    color: $color-text-danger !important;

    // links
    @at-root a#{&} {
        &:focus {
            color: $color-text-danger-link !important;
        }

        &:hover {
            color: $color-text-danger-link !important;
        }
    }
}

</style>
