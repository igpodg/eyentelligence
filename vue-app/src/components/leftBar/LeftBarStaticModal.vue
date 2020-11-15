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
        team: Object
    },
    methods: {
        sendCurrentTeam: function() {
            if (this.team !== undefined)
                this.$eventBus.$emit("leftbar-team-selected", this.team);
        }
    }
}
</script>

<style scoped lang="scss">

</style>
