<template>
    <secondary-form title="You've been invited to Eyentelligence!"
                    subtitle="Invited by: SuperTeam (John Johnson)">
        <secondary-link url="/login" tall>I already have an account</secondary-link>
        <secondary-main-text>
            Please enter your desired credentials to register
            in the system and join your colleagues:
        </secondary-main-text>
        <secondary-input id="username" name="Username" required/>
        <secondary-input id="password" name="Password" type="password" required/>
        <div class="form-row">
            <secondary-dropdown cols="3" id="title" name="Title"
                                :data="['Mr.', 'Mrs.', 'Ms.', 'Dr.']"/>
            <secondary-input cols="4" id="firstname" name="First name" required/>
            <secondary-input cols="5" id="lastname" name="Last name" required/>
        </div>
        <secondary-button color="success" name="Done"/>
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
    name: "Invited",
    components: {
        SecondaryForm,
        SecondaryMainText,
        SecondaryLink,
        SecondaryDropdown,
        SecondaryInput,
        SecondaryButton
    }
}
</script>
