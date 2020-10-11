<template>
    <li class="list-inline-item">
        <div class="dropdown xp-message">
            <a class="dropdown-toggle text-white" href="#" role="button" id="xp-message" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                <i class="icon-speech font-18 v-a-m"></i>&nbsp;
                <span v-if="badge !== undefined" class="badge badge-pill bg-success-gradient xp-badge-up">
                    {{ badge }}
                </span>
            </a>
            <popup name="message" title="8 New Messages" bottom-text="View all">
                <popup-item-msg icon-url="img/user-message.jpg" title="Ariel Blue" time="3 min ago" :badge="2">
                    Thank you for attending...
                </popup-item-msg>
                <popup-item-msg icon-url="img/user-message.jpg" title="Jammy Moon" time="5 min ago" :badge="3">
                    Hey no worries! Trust me...
                </popup-item-msg>
                <popup-item-msg icon-url="img/user-message.jpg" title="Lisa Ross" time="5:25 PM" :badge="5">
                    Remedies for colic? i don't...
                </popup-item-msg>
            </popup>
        </div>
    </li>
</template>

<script>
import Popup from "@/components/popup/Popup.vue";
import PopupItemMsg from "@/components/popup/PopupItemMsg.vue";

export default {
    name: "TopBarMessages",
    components: {
        Popup,
        PopupItemMsg
    },
    props: {
        badge: Number
    }
}
</script>

<style scoped lang="scss">

</style>
