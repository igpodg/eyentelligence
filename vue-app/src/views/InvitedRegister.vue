<template>
    <secondary-form :title="isRegister ? registerTitle : invitedTitle"
                    :subtitle="isRegister ? undefined : 'Invited by: SuperTeam (John Johnson)'">
        <secondary-link url="/login" tall>I already have an account</secondary-link>
        <secondary-main-text>
            Please enter your desired credentials to register
            in the system and join your colleagues:
        </secondary-main-text>
        <secondary-dropdown id="team" name="Team to join"
                            :data="availableTeams" v-if="isRegister"/>
        <hr v-if="isRegister">
        <secondary-input id="username" name="Username" is-required/>
        <secondary-input id="password" name="Password" type="password" is-required/>
        <div class="form-row">
            <secondary-dropdown :cols="3" id="title" name="Title"
                                :data="titles"/>
            <secondary-input :cols="4" id="firstname" name="First name" is-required/>
            <secondary-input :cols="5" id="lastname" name="Last name" is-required/>
        </div>
        <secondary-button color="success" name="Begin the work!"/>
    </secondary-form>
</template>

<script>
import SecondaryForm from "@/components/secondary/SecondaryForm.vue";
import SecondaryMainText from "@/components/secondary/SecondaryMainText.vue";
import SecondaryLink from "@/components/secondary/SecondaryLink.vue";
import SecondaryDropdown from "@/components/secondary/SecondaryDropdown.vue";
import SecondaryInput from "@/components/secondary/SecondaryInput.vue";
import SecondaryButton from "@/components/secondary/SecondaryButton.vue";

export default {
    name: "InvitedRegister",
    components: {
        SecondaryForm,
        SecondaryMainText,
        SecondaryLink,
        SecondaryDropdown,
        SecondaryInput,
        SecondaryButton
    },
    data: function() {
        return {
            titles: ["Mr.", "Mrs.", "Ms.", "Dr."],
            availableTeams: [],
            isRegister: false,
            invitedTitle: "You've been invited to Eyentelligence!",
            registerTitle: "Register in Eyentelligence"
        }
    },
    created: function() {
        this.availableTeams = this.$root.$children[0]
            .teams.map(team => team.name);
    },
    beforeRouteEnter: function(to, from, next) {
        next(function(that) {
            that.isRegister = that.$route.name === "Register";
        });
    }
}
</script>
