<template>
    <li>
        <a href="#" :class="(color === undefined) ? '' : 'text-' + color"
           data-toggle="modal" :data-target="'#' + target" @click="sendCurrentTeam">
            {{ name }}
        </a>
    </li>
</template>

<script>
export default {
    name: "LeftBarStaticModal",
    props: {
        name: String,
        color: String,
        target: String,
        team: Object,
        owner: {
            type: Boolean,
            default: false
        }
    },
    methods: {
        sendCurrentTeam: function() {
            if (this.team !== undefined)
                this.$eventBus.$emit("leftbar-team-selected", this.team, this.owner);
        }
    }
}
</script>

<style scoped lang="scss">

</style>
