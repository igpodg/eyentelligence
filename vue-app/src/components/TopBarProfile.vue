<template>
    <li class="list-inline-item mr-0">
        <div class="dropdown xp-userprofile">
            <a class="dropdown-toggle" href="#" role="button" id="xp-userprofile" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                <img :src="avatarUrl" alt="user-profile" class="rounded-circle img-fluid">
                <span class="xp-user-live"></span>
            </a>

            <popup-mini name="userprofile" title="Welcome, John Doe">
                <popup-mini-item color="primary" icon="user" title="Profile"/>
                <popup-mini-item color="success" icon="wallet" title="Billing"/>
                <popup-mini-item color="warning" icon="settings" title="Setting"/>
                <popup-mini-item color="info" icon="lock" title="Lock Screen"/>
                <popup-mini-item color="danger" icon="power" title="Logout"/>
            </popup-mini>
        </div>
    </li>
</template>

<script>
import PopupMini from "@/components/PopupMini.vue";
import PopupMiniItem from "@/components/PopupMiniItem.vue";

export default {
    name: "TopBarProfile",
    components: {
        PopupMini,
        PopupMiniItem
    },
    props: {
        avatarUrl: String
    }
}
</script>

<style scoped lang="scss">

</style>
