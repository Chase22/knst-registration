<script lang="ts">
    import {Head, Row, Cell} from '@smui/data-table';
    import IconButton from "@smui/icon-button";
</script>


<Head>
    <Row>
        <Cell numeric columnId="id">
            <IconButton class="material-icons">arrow_upward</IconButton>
            Id
        </Cell>
        <Cell columnId="username">Username
            <IconButton class="material-icons">arrow_upward</IconButton>
        </Cell>
        <Cell columnId="firstName">Name
            <IconButton class="material-icons">arrow_upward</IconButton>
        </Cell>
        <Cell columnId="dateOfBirth">Geburtstag
            <IconButton class="material-icons">arrow_upward</IconButton>
        </Cell>
        <Cell columnId="registryDate">Anmeldezeit
            <IconButton class="material-icons">arrow_upward</IconButton>
        </Cell>
        <Cell numeric columnId="companies">
            <IconButton class="material-icons">arrow_upward</IconButton>
            Begleitung
        </Cell>
    </Row>
</Head>