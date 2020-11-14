<template>
    <div class="create-team">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-xl-7">
                <card heading="Team details" subtitle="Here you can fill in
                    the basic information for the new team.">
                    <form class="create-team-body" ref="createform" @submit="formSubmitted">
                        <form-row label="Name" :size="2">
                            <form-input id="createTeamName" is-required
                                        tip="This is a required field.
                                            It will be displayed across the system."/>
                        </form-row>
                        <form-row label="Type" :size="2">
                            <form-dropdown id="createTeamType" is-required
                                           :items="teamTypes"
                                           tip="This is a required field."/>
                        </form-row>
                        <form-row label="Location" :size="2">
                            <form-dropdown id="createTeamParent"
                                           :items="[]"/>
                        </form-row>
                        <form-button label="Create" size="5"/>
                    </form>
                </card>
            </div>
        </div>
    </div>
</template>

<script>
import Card from "@/components/card/Card.vue";
import FormRow from "@/components/FormRow.vue";
import FormInput from "@/components/FormInput.vue";
import FormDropdown from "@/components/FormDropdown.vue";
import FormButton from "@/components/FormButton.vue";

export default {
    name: "CreateTeam",
    components: {
        Card,
        FormRow,
        FormInput,
        FormDropdown,
        FormButton
    },
    data: function() {
        return {
            teamTypes: [
                {id: "O", label: "Organization"},
                {id: "T", label: "Regular team"}
            ]
        }
    },
    methods: {
        formSubmitted: function(event) {
            event.preventDefault();
            //for (let el of this.$refs.createform.elements) {
            //    if (el.id === "firstname")
            //        newData.firstName = el.value;
            //    else if (el.id === "lastname")
            //        newData.lastName = el.value;
            //}
            return false;
        }
    }
}
</script>

<style scoped lang="scss">

</style>
