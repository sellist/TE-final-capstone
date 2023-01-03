// Worker portal, includes list of assignments to that worker, and an option to
finish that task

<template>
    <div>
        <worker-requests :workerId="$route.params.workerId" />
    </div>
</template>

<script>
import WorkerRequests from "../components/WorkerRequests.vue";
export default {
    components: { WorkerRequests },
};
</script>

<style scoped></style>
