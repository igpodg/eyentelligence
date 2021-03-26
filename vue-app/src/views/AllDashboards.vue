<template>
    <div class="all-dashboards">
        <card :heading="teamName + '\'s Dashboards'"
              subtitle="See a detailed list of all dashboards available for the team."
              :perform-func="createNewDashboard" button-icon="note" button-text="Create New">
            <div class="row alldash-row">
                <div class="col-md-12 col-lg-12 col-xl-6">
                    <h4 class="alldash-error" v-if="dashboards.length === 0">
                        No dashboards have been found for this team yet!
                    </h4>
                    <div v-else v-for="(dash, i) in dashboards" :key="dash.id">
                        <router-link :to="'/team/' + dash.team.id + '/dashboard/' + dash.id">
                            <form-element :label="dash.name"/>
                        </router-link>
                        <div>
                            <span><b>Created on:</b> {{ timestampToDate(dash.createdDateTime) }}</span><br>
                            <span><b>Modified on:</b> {{ timestampToDate(dash.lastModifiedDateTime) }}</span>
                        </div>
                        <form-button class="alldash-delete" label="Delete" :size="5" color="danger"
                                     :directclick="dash.id"/>
                        <hr v-if="i !== dashboards.length-1">
                    </div>
                </div>
            </div>
        </card>
    </div>
</template>

<script>
import Card from "@/components/card/Card.vue";
import FormButton from "@/components/form/FormButton.vue";
import FormElement from "@/components/form/FormElement.vue";

export default {
    name: "AllDashboards",
    components: {
        Card,
        FormButton,
        FormElement
    },
    data: function() {
        return {
            teamId: null,
            dashboards: [],
            getTeamById: null,
            teamName: ""
        }
    },
    methods: {
        updateDashboards: function() {
            let teamId = parseInt(this.$route.params.id);
            this.teamId = teamId;
            if (teamId in this.$root.$children[0].dashboards)
                this.dashboards = this.$root.$children[0].dashboards[teamId];
            else
                this.dashboards = [];
        },
        updateTeamName: function() {
            let team = this.getTeamById(this.teamId);
            this.teamName = team.name;
        },
        timestampToDate: function(timestamp) {
            let date = new Date(timestamp * 1000);
            return date.toLocaleString("default", {
                day: "numeric",
                month: "long",
                year: "numeric",
                hour: "numeric",
                hourCycle: "h23",
                minute: "numeric",
                second: "numeric"
            });
        },
        createNewDashboard: function() {
            let prefix = "My Dashboard #";
            let counter = 1;
            let existingNames = this.dashboards.map(dash => dash.name);
            while (existingNames.includes(prefix + counter))
                counter++;
            let toSend = {
                team: {"id": this.teamId},
                name: prefix + counter
            };

            this.$logDetailed("Creating a new dashboard ...");
            this.$fetchSync("https://127.0.0.1:9090/dashboard", {
                method: "POST",
                headers: { "X-API-Key": "xxxxxxxx" },
                body: JSON.stringify(toSend)
            });
            this.$logDetailed("Dashboard creation finished.");
            this.$eventBus.$emit("update-dashboards");
            this.updateDashboards();
        },
        deleteDashboard: function(id) {
            this.$logDetailed("Deleting dashboard with ID " + id + "...");
            this.$fetchSync("https://127.0.0.1:9090/dashboard/" + id, {
                method: "DELETE",
                headers: { "X-API-Key": "xxxxxxxx" }
            });
            this.$logDetailed("Deleting the dashboard finished.");
            this.$eventBus.$emit("update-dashboards");
            this.updateDashboards();
        }
    },
    mounted: function() {
        this.getTeamById = this.$root.$children[0].getTeamById;
        this.updateDashboards();
        this.updateTeamName();
        this.$eventBus.$on("delete-dashboard", this.deleteDashboard);
    },
    beforeDestroy: function() {
        this.$eventBus.$off("delete-dashboard");
    },
    watch: {
        $route: function() {
            this.updateDashboards();
            this.updateTeamName();
        }
    }
}
</script>

<style lang="scss">
.alldash {
    &-row {
        justify-content: center;
    }

    &-delete {
        padding-top: 0 !important;
        padding-bottom: 0 !important;
    }

    &-error {
        $paddingval: 60px;
        padding-top: $paddingval;
        padding-bottom: $paddingval;
    }
}

</style>
