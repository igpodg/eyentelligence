<template>
    <li class="list-inline-item">
        <div class="dropdown xp-notification">
            <a class="dropdown-toggle text-white" href="#" role="button" id="xp-notification" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                <i class="icon-bell font-18 v-a-m"></i>&nbsp;
                <span v-if="badge !== undefined" class="badge badge-pill bg-danger-gradient xp-badge-up">
                    {{ badge }}
                </span>
            </a>
            <popup name="notification" title="3 New Notifications" bottom-text="View all">
                <popup-item-noti color="primary" icon="user-follow" time="2 min ago">
                    New user registered
                </popup-item-noti>
                <popup-item-noti color="success" icon="basket-loaded" time="8:45 PM">
                    New order placed
                </popup-item-noti>
                <popup-item-noti color="danger" icon="like" time="Yesterday">
                    John like your photo.
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
.font-18 {
    font-size: 18px;
}

.v-a-m {
    vertical-align: middle;
}

.bg-danger-gradient {
    background-image: linear-gradient(58deg, #ff4b5b 0, #ff7e4b 100%);
}


</style>
