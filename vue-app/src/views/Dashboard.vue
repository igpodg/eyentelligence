<template>
    <div class="card m-b-30" style="height: 74vh;">
        <div class="card-header bg-white" ref="header1">
            <button type="button" class="btn btn-rounded btn-outline-primary">
                <i class="icon-refresh mr-2"></i>&nbsp;Refresh
            </button>
            <div style="float: right;">
                <button type="button" class="btn btn-rounded btn-outline-primary"
                        @click="toggleEdit">
                    <i class="icon-pencil mr-2"></i>&nbsp;Edit
                </button>
            </div>
        </div>
        <div class="card-header bg-white dashboard-edit-disabled" ref="header2">
            <div style="float: right;">
                <button type="button" class="btn btn-rounded btn-outline-primary">
                    <i class="icon-plus mr-2"></i>&nbsp;Add Tile
                </button>&nbsp;
                <button type="button" class="btn btn-rounded btn-outline-primary">
                    <i class="icon-equalizer mr-2"></i>&nbsp;Manage Filters
                </button>&nbsp;
                <button type="button" class="btn btn-rounded btn-outline-success"
                        @click="toggleEdit">
                    <i class="icon-check mr-2"></i>&nbsp;Done
                </button>
            </div>
        </div>

        <div class="card-body">
            <div class="card m-b-30"
                 :style="'width: 270px !important; border: solid ' +
                    (editEnabled ? '#4c7cf3' : 'transparent') + '; float: left;'">
                <div class="card-body" style="padding: 3.25rem !important; text-align: center;">
                    <span style="position: absolute; margin-top: -30px; margin-left: 95px; cursor: pointer;"
                          class="icon-options-vertical" v-if="editEnabled"/>
                    <h1 class="text-success-gradient mb-3"><i class="icon-arrow-up mr-2"></i>58%</h1>
                    <h5>test</h5>
                </div>
            </div>
            <span style="width: 30px; float: left; visibility: hidden;">.</span>
            <div class="card m-b-30" style="width: 365px !important; border: solid transparent; height: 420px;">
                <div class="card-body"
                     style="padding: 3.25rem !important; text-align: center; height: 420px;">
                    <span style="position: absolute; margin-top: -30px; margin-left: 270px; cursor: pointer;"
                          class="icon-options-vertical" v-if="editEnabled"/>
                    <dashboard-temp-svg/>
                </div>
            </div>
        </div>
        <!-- End XP Row -->
    </div>
</template>

<script>
import DashboardTempSvg from "@/components/DashboardTempSvg.vue";

export default {
    name: "Dashboard",
    components: {
        DashboardTempSvg
    },
    data: function() {
        return {
            editEnabled: false,
            getDashboardById: null,
            dashId: null,
            name: ""
        }
    },
    methods: {
        updateDashboard: function() {
            let parsedTeamId = parseInt(this.$route.params.id);
            let parsedDashId = parseInt(this.$route.params.dashid);
            this.dashId = parsedDashId;
            let dash = this.getDashboardById(parsedTeamId, parsedDashId);
            this.name = dash.name;
        },
        toggleEdit: function() {
            this.$refs.header1.classList.toggle("dashboard-edit-disabled");
            this.$refs.header2.classList.toggle("dashboard-edit-disabled");
            this.editEnabled = !this.editEnabled;
            this.$eventBus.$emit("being-edited");
        },
        sendNewDashboardName: function(newName) {
            this.$logDetailed("Sending a new name for dashboard #" + this.dashId + "...");
            this.$fetchSync("https://127.0.0.1:9090/dashboard/" + this.dashId, {
                method: "PUT",
                headers: { "X-API-Key": "xxxxxxxx" },
                body: "{\"name\": \"" + newName + "\"}"
            });
            this.$logDetailed("Finished updating to the new name.");
            this.$eventBus.$emit("update-dashboards");
        }
    },
    mounted: function() {
        this.$eventBus.$on("new-dashboard-name", this.sendNewDashboardName);
        this.getDashboardById = this.$root.$children[0].getDashboardById;
        this.updateDashboard();
    },
    beforeDestroy: function() {
        this.$eventBus.$off("new-dashboard-name");
    },
    watch: {
        $route: function() {
            this.updateDashboard();
        }
    }
}
</script>

<style lang="scss">
.dashboard-edit-disabled {
    display: none;
}




.m-b-30 {
    margin-bottom: 30px;
}

.text-success-gradient {
    background: -webkit-linear-gradient(#9acd2b, #2bcd72);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
}

.bg-white {
    background-color: #ffffff !important;
}

.btn {
    border-radius: 3px;
    font-size: 14px;
    padding: 7px 18px;
}

.btn-rounded {
    border-radius: 20px;
}

.btn-outline-primary {
    color: #4c7cf3;
    border-color: #4c7cf3;
}

.btn-outline-primary:active {
    background-color: #346bf1;
    border-color: #346bf1;
}
.btn-outline-primary:hover {
    background-color: #346bf1;
    border-color: #346bf1;
}

.card {
    border: none;
    box-shadow: 0 0 30px 0 rgba(#c8c8c8, 0.2);
    border-radius: 15px;
}

.card-header {
    border-bottom: 1px solid rgba(#2b343a, 0.05);
}

//.card-header:first-child {
//    border-radius: calc(15px - 1px) calc(15px - 1px) 0 0;
//}

.card-header {
    border-radius: calc(15px - 1px) calc(15px - 1px) 0 0 !important;
}

.ct-grid {
    stroke: #e1e4e9;
    stroke-width: 1px;
    stroke-dasharray: 3px;
}

.ct-label {
    fill: #8a98ac;
    color: #8a98ac;
    font-size: 14px;
}

.ct-series-a .ct-bar {
    stroke: #4c7cf3;
}

.ct-series-b .ct-bar {
    stroke: #2bcd72;
}






.ct-label {
    fill: #8a98ac;
    color: #8a98ac;
    font-size: 14px;
    line-height: 1;
}

.ct-label.ct-vertical.ct-start {
    -webkit-box-align: flex-end;
    -webkit-align-items: flex-end;
    -ms-flex-align: flex-end;
    align-items: flex-end;
    -webkit-box-pack: flex-end;
    -webkit-justify-content: flex-end;
    -ms-flex-pack: flex-end;
    justify-content: flex-end;
    text-align: right;
    text-anchor: end;
}

.ct-chart-bar .ct-label, .ct-chart-line .ct-label {
    display: block;
    display: -webkit-box;
    display: -moz-box;
    display: -ms-flexbox;
    display: -webkit-flex;
    display: flex;
}




h1 {
    @media (max-width: 991px) {
        font-size: 38px;
    }

    @media (max-width: 767px) {
        font-size: 36px;
    }
}

h5 {
    @media (max-width: 991px) {
        font-size: 18px;
    }

    @media (max-width: 767px) {
        font-size: 16px;
    }
}


</style>
