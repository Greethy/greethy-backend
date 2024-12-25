# Greethy Account

1. Saga Flow
- `Account Service` receives a request to register a new User.
- It checks with 'Keycloak' (an IdP) if the username (and email) is existed.
- If no, 'Keycloak' will save the user.