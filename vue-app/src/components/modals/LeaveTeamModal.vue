<template>
    <div class="modal fade" id="xpLeaveModal" tabindex="-1" role="dialog" aria-labelledby="xpLeaveModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="xp-modalhbar">
                        <form>
                            <h5 class="text-black">Are you sure you want to leave SuperTeam?</h5>
                            <div class="text-warning">Warning: By leaving the team as an Owner, a new Owner will have to be assigned.</div>
                            <button type="button" class="btn btn-danger first-button">Leave Team</button>
                            <button type="button" class="btn btn-light">Cancel</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "LeaveTeamModal",
    props: {
        a: String
    }
}
</script>

<style scoped lang="scss">
.modal-dialog {
    max-width: 700px;
}

.modal-body {
    padding: 40px 60px 40px 60px;
}

form .text-black {
    padding-bottom: 15px;
}

form .text-warning {
    padding-bottom: 80px;
}

.first-button {
    margin-right: 15px;
}
</style>
