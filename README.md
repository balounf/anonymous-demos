# anonymous-demos

[![CI Build](https://github.com/axonivy-market/anonymous-demos/actions/workflows/ci.yml/badge.svg)](https://github.com/axonivy-market/anonymous-demos/actions/workflows/ci.yml)

**Core idea**  
Instead of registering users for every external user, use e-mails with randomly generated and unguessable tokens.
All requests to external users are done via e-mails containeing such tokens. Internal processes are started or
continued by sending them special signals.

**Use Cases**
* Start an unauthenticated process from AxonIvy, verify the user's e-email address and start an internal AxonIvy process.
* Start an unauthenticated process from any web form on any external web-page by the form's `action` attribute and their POST request. Verify the user's email-address and start an internal AxonIvy process.
* During a running internal process, interact with a user by e-mail and wait for their response efficiently.

Read our [documentation](anonymous-demos-product/README.md).
