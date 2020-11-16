<template>
    <secondary-form title="Recover to Eyentelligence" tall>
        <secondary-main-text>
            Here you can recover your account (lost username or password).
        </secondary-main-text>
        <p>...</p><p>...</p>
        <secondary-link url="/login">Go back to login</secondary-link>
        <secondary-button color="primary" name="Recover my account"/>
    </secondary-form>
</template>

<script>
import SecondaryForm from "@/components/secondary/SecondaryForm.vue";
import SecondaryMainText from "@/components/secondary/SecondaryMainText.vue";
import SecondaryLink from "@/components/secondary/SecondaryLink.vue";
import SecondaryButton from "@/components/secondary/SecondaryButton.vue";

export default {
    name: "Recover",
    components: {
        SecondaryForm,
        SecondaryMainText,
        SecondaryLink,
        SecondaryButton
    }
}
</script>
