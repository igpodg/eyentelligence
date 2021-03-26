<template>
    <li class="list-inline-item">
        <div class="dropdown xp-notification">
            <a class="dropdown-toggle" href="#" role="button" id="xp-notification" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                <i class="icon-bell"></i>&nbsp;
                <span v-if="badge !== undefined" class="badge badge-pill bg-danger-gradient xp-badge-up">
                    {{ badge }}
                </span>
            </a>
            <popup name="notification" title="3 New Notifications" bottom-text="View all">
                <popup-item-noti color="primary" icon="user-follow" time="2 min ago">
                    New user invited
                </popup-item-noti>
                <popup-item-noti color="success" icon="user-follow" time="8:45 PM">
                    New user registered
                </popup-item-noti>
                <popup-item-noti color="danger" icon="close" time="Yesterday">
                    My Dashboard #2 removed
                </popup-item-noti>
            </popup>
        </div>
    </li>
</template>

<script>
import Popup from "@/components/popup/Popup.vue";
import PopupItemNoti from "@/components/popup/PopupItemNoti.vue";

export default {
    name: "TopBarNotifications",
    components: {
        Popup,
        PopupItemNoti
    },
    props: {
        badge: Number
    }
}
</script>

<style scoped lang="scss">
$color-white: #ffffff;

.dropdown-toggle {
    color: $color-white !important;

    &>i {
        font-size: 18px;
        vertical-align: middle;
    }
}

@mixin background-gradient($name, $begin, $end) {
    .bg-#{$name}-gradient {
        background-image: linear-gradient(58deg, $begin 0, $end 100%);
    }
}

$gradient-danger-begin: #ff4b5b;
$gradient-danger-end: #ff7e4b;

@include background-gradient("danger", $gradient-danger-begin, $gradient-danger-end);

</style>
