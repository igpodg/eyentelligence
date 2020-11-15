<template>
    <div class="team-home">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-xl-7">
                <card heading="Last Activity" subtitle="Here you can see what recent actions
                    have been taken by the team members.">
                    <div class="team-home-body">
                        <activity-row name="Anderea Carson" :rank="1" last
                                      tip="Resized tile &quot;Yearly&quot;" time="Today, 14:32"/>
                        <hr>
                        <activity-row name="John Johnson" :rank="2" small
                                      tip="Changed &quot;My Dashboard #2&quot; visibility" time="Mon, 08:50"/>
                        <activity-row name="Emily Watson" :rank="3" small
                                      tip="No activity" time="Mon, 09:46"/>
                        <activity-row name="Mike Anders" :rank="4" small last
                                      tip="Resized tile &quot;Monthly Sales&quot;" time="Thu, 16:26"/>
                    </div>
                </card>
            </div>
        </div>
    </div>
</template>

<script>
import Card from "@/components/card/Card.vue";
import ActivityRow from "@/components/ActivityRow.vue";

export default {
    name: "TeamHome",
    components: {
        Card,
        ActivityRow
    }
}
</script>

<style scoped lang="scss">

</style>
