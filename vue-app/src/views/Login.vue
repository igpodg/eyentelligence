<template>
    <secondary-form title="Log in to Eyentelligence" tall>
        <secondary-main-text>
            Please enter your username and password:
        </secondary-main-text>
        <secondary-input id="username" name="Username" is-required/>
        <secondary-input id="password" name="Password" type="password" is-required/>
        <secondary-link url="/recover">Recover my account</secondary-link>
        <secondary-link url="/register">Register a new account</secondary-link>
        <secondary-button color="primary" name="Log in"/>
    </secondary-form>
</template>

<script>
import SecondaryForm from "@/components/secondary/SecondaryForm.vue";
import SecondaryMainText from "@/components/secondary/SecondaryMainText.vue";
import SecondaryLink from "@/components/secondary/SecondaryLink.vue";
import SecondaryInput from "@/components/secondary/SecondaryInput.vue";
import SecondaryButton from "@/components/secondary/SecondaryButton.vue";

export default {
    name: "Login",
    components: {
        SecondaryForm,
        SecondaryMainText,
        SecondaryLink,
        SecondaryInput,
        SecondaryButton
    }
}
</script>
