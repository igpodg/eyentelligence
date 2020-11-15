<template>
    <li :class="isActive() ? 'active' : ''">
        <router-link :to="url" :class="isActive() ? 'active' : ''">
            <i :class="'icon-' + icon"></i><span>{{ name }}</span>
        </router-link>
    </li>
</template>

<script>
export default {
    name: "LeftBarStatic",
    props: {
        icon: String,
        name: String,
        internalName: String,
        url: String
    },
    data: function() {
        return {
            activeRoutes: ["Overview", "Statistics", "JoinTeam", "CreateTeam"],
            currentName: (this.internalName === undefined) ? this.name : this.internalName
        }
    },
    methods: {
        isActive: function() {
            //console.log(this.$route.name);
            let that = this;
            return this.activeRoutes.some(function(route) {
                return that.currentName === route && that.$route.name === route;
            });
        }
    }
}
</script>

<style scoped lang="scss">

</style>
