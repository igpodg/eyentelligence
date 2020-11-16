<template>
    <li class="list-inline-item mr-0">
        <div class="dropdown xp-userprofile">
            <a class="dropdown-toggle" href="#" role="button" id="xp-userprofile" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                <img :src="avatarUrl" alt="user-profile" class="rounded-circle img-fluid">
                <span class="xp-user-live"></span>
            </a>

            <popup-mini name="userprofile" :title="'Welcome, ' + getFullName()">
                <!--<popup-mini-item color="primary" icon="user" title="Profile"/>
                <popup-mini-item color="success" icon="wallet" title="Billing"/>
                <popup-mini-item color="warning" icon="settings" title="Setting"/>
                <popup-mini-item color="info" icon="lock" title="Lock Screen"/>
                <popup-mini-item color="danger" icon="power" title="Logout"/>-->
                <popup-mini-item color="success" icon="envelope" title="Got Invited" url="/invited/TOKEN"/>
                <popup-mini-item color="success" icon="note" title="Register" url="/register"/>
                <popup-mini-item color="primary" icon="key" title="Log In" url="/login"/>
                <popup-mini-item color="danger" icon="power" title="Log Out" url="/logout"/>
            </popup-mini>
        </div>
    </li>
</template>

<script>
import PopupMini from "@/components/popup/PopupMini.vue";
import PopupMiniItem from "@/components/popup/PopupMiniItem.vue";

export default {
    name: "TopBarProfile",
    components: {
        PopupMini,
        PopupMiniItem
    },
    props: {
        avatarUrl: String,
        user: Object
    },
    methods: {
        getFullName: function() {
            return [this.user.firstName,
                this.user.middleName,
                this.user.lastName]
                .filter(Boolean)
                .join(" ");
        }
    }
}
</script>

<style scoped lang="scss">
$color-white: #ffffff;

$gradient-success-begin: #2bcd72;
$gradient-success-end: #9acd2b;

.xp-userprofile {
    a {
        img {
            width: 40px;
        }

        .xp-user-live {
            position: absolute;
            bottom: -2px;
            right: -2px;
            width: 12px;
            height: 12px;
            border-radius: 50%;
            border: 2px solid $color-white;
            background-image: linear-gradient(58deg,
                $gradient-success-begin 0,
                $gradient-success-end 100%);
        }
    }
}

</style>
