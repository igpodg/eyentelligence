<template>
    <div class="modal fade" :id="name" :ref="name" tabindex="-1" role="dialog" :aria-labelledby="name + 'Label'" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="xp-modalhbar">
                        <form>
                            <h5 class="text-heading">Are you sure you want to leave {{ this.teamName }}?</h5>
                            <div class="text-danger">Warning: If this Team contains subteams,
                                all of them will be removed upon leaving!</div>
                            <button type="button" class="btn btn-danger main-button" @click="leaveTeam">Leave Team</button>&nbsp;
                            <button type="button" class="btn btn-light" @click="closeLeaveTeam">Cancel</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "LeaveTeamModal",
    props: {
        name: {
            type: String,
            default: "xpLeaveModal"
        }
    },
    data: function() {
        return {
            teamId: null,
            teamName: ""
        }
    },
    methods: {
        setTeamIdName: function(team) {
            this.teamId = team.id;
            this.teamName = team.name;
        },
        leaveTeam: function() {
            this.$logDetailed("Leaving Team with ID " + this.teamId + "...");
            this.$fetchSync("https://127.0.0.1:9090/team/" + this.teamId, {
                method: "DELETE",
                headers: { "X-API-Key": "xxxxxxxx" }
            });
            this.$logDetailed("Team leaving finished.");
            this.$eventBus.$emit("update-teams");
            this.closeLeaveTeam();
        },
        closeLeaveTeam: function() {
            this.$refs[this.name].click();
        }
    },
    mounted: function() {
        //this.getTeamById = this.$root.$children[0].getTeamById;
        //this.teamName = this.getTeamById(0).name;
        this.$eventBus.$on("leftbar-team-selected", this.setTeamIdName);
    },
    beforeDestroy: function() {
        this.$eventBus.$off("leftbar-team-selected");
    }
}
</script>

<style scoped lang="scss">
$color-white: #ffffff;

.modal-dialog {
    max-width: 700px;
}

.modal-body {
    padding: 40px 60px 40px 60px;
}

$color-heading: #2b343a;
$color-subtitle: #fac751;

form {
    .text-heading {
        color: $color-heading !important;
        padding-bottom: 15px;
    }

    .text-subtitle {
        color: $color-subtitle !important;
        padding-bottom: 80px;
    }

    .text-danger {
        padding-bottom: 80px;
    }
}

.main-button {
    margin-right: 15px;
}

$color-button-danger: #ff4b5b;
$color-button-danger-text: $color-white;
$color-button-danger-selected: #ff3244;
$color-button-danger-shadow: #ffb1b8;

$color-button-light: #e1e4e9;
$color-button-light-text: #8a98ac;
$color-button-light-hover: #212529;
$color-button-light-selected: #d2d7de;
$color-button-light-shadow: $color-white;

%button-danger {
    color: $color-button-danger-text;
    background-color: $color-button-danger;
    border-color: $color-button-danger;
}

%button-danger-selected {
    background-color: $color-button-danger-selected;
    border-color: $color-button-danger-selected;
}

%button-light {
    color: $color-button-light-text;
    background-color: $color-button-light;
    border-color: $color-button-light;
}

%button-light-selected {
    background-color: $color-button-light-selected;
    border-color: $color-button-light-selected;
}

.btn {
    &-danger {
        @extend %button-danger;
        box-shadow: 0 2px 6px 0 rgba($color-button-danger, 0.5);
        &:disabled { @extend %button-danger; }
        &:focus { box-shadow: 0 0 0 0.2rem $color-button-danger-shadow; }

        &:active { @extend %button-danger-selected; }
        &:hover { @extend %button-danger-selected; }
        &:focus { @extend %button-danger-selected; }
        &:visited { @extend %button-danger-selected; }
        &:not(:disabled):not(.disabled):active {
            @extend %button-danger-selected;
        }
    }

    &-light {
        @extend %button-light;
        box-shadow: 0 2px 6px 0 rgba($color-button-light, 0.5);
        &:disabled { @extend %button-light; }
        &:focus { box-shadow: 0 0 0 0.2rem $color-button-light-shadow; }
        &:hover { color: $color-button-light-hover; }

        &:active { @extend %button-light-selected; }
        &:hover { @extend %button-light-selected; }
        &:focus { @extend %button-light-selected; }
        &:visited { @extend %button-light-selected; }
        &:not(:disabled):not(.disabled):active {
            @extend %button-light-selected;
        }
    }
}

</style>
