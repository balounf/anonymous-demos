<!--
Dear developer!     

When you create your very valuable documentation, please be aware that this Readme.md is not only published on github. This documentation is also processed automatically and published on our website. For this to work, the two headings "Demo" and "Setup" must not be changed. Do also not change the order of the headings. Feel free to add sub-sections wherever you want.
-->

# anonymous-demos

**Core idea**  
Instead of registering users for every external user, use e-mails with randomly generated and unguessable tokens.
All requests to external users are done via e-mails containeing such tokens. Internal processes are started or
continued by sending them special signals.

**Use Cases**
* Start an unauthenticated process from AxonIvy, verify the user's e-email address and start an internal AxonIvy process.
* Start an unauthenticated process from any web form on any external web-page by the form's `action` attribute and their POST request. Verify the user's email-address and start an internal AxonIvy process.
* During a running internal process, interact with a user by e-mail and wait for their response efficiently.

<!--
The explanations under "MY-RRODUCT-NAME" are displayed  e.g. for the Connector A-Trust here: https://market.axonivy.com/a-trust#tab-description   
-->

## Demo

The demos show a fictional registration use-case, where an unauithenticated user enters their name and e-mail address to register. In the next step, the user's e-mail will be verified by sending a link with a random token to the user's e-email address. The token is stored in the AxonIvy *Business Data Repository* and if the user follows the token link, the token will be invalidated and an internal process is started by a signal.

For demonstration reasons, the internal process will be viewed by an approver who will find out, that some information of the external user is needed (their social security number). The approver requests the additional data from the external user with another token e-email.

When the user follows the link in the e-mail, they will be shown a dialog where they can enter the missing data. After submission, the process continues. The external user will get a confirmation e-mail and the internal user an overview page to show the completed data.

When the approver continues from this final dialog, the process will end.


**Workflow**
All demo variants use the same workflow:

* Start the demo as unauthenticated user and enter name and e-mail address
* Wait for an e-mail verification e-mail and follow the link in this e-mail
* A task for the role `AnonymousUserApprover` will be created. In this task, additional data (social security number) will be requested from the external user.
* As unauthenticated user open your e-mail to find the additional data request, follow the link and enter the missing data.
* You will receive an e-mail confirming the complete registration process.
* A final confirmation task for the role `AnonymousUserApprover` will be created. After continuing from this task, the demo ends
* A small variant shows how a user owning the role `AnonymousUserApprover` can skip the waiting for the external user to enter their additional data. This can be used to terminate waiting in case the additional information was entered manually by some other means.

**Scenarios**

The demos can be run in the following scenarios:

A. Standalone in the AxonIvy Designer
B. With the Apache Web Server and the AxonIvy Designer
C. With the Apache Web Server, the AxonIvy Engine and a Test E-Mail Server
<!--
We use all entries under the heading "Demo" for the demo-Tab on our Website, e.g. for the Connector A-Trust here: https://market.axonivy.com/a-trust#tab-demo  
-->

## Setup

***Variant A, AxonIvy Designer***

Assuming your AxonIvy Designer runs at the default port, open the link: http://localhost:8081/designer/pro/anonymous-demos-open/19C23640F9AD30D8/register.ivp to start the demo.

Open the *Email Messages* view in AxonIvy Designer to see any e-mails generated.

To work on internal tasks, in a second browser open the workflow UI at http://localhost:8081 (or install the Portal if you like).

***Variant B, Apache WebServer and AxonIvy Designer***

This variant uses docker containers to supply the demo environment. Make sure, you have docker installed, open a console or shell and go to the directory `anonymous-demos-extra/docker`

Start the containers with the command:

`docker-compose up -d`

This should start all containers.

Open the link http://localhost:81/ to start the demo.

Continue in the same way as in variant A.

***Variant C, Apache WebServer, AxonIvy Engine, Smtp4Dev Email Test Server***

This variant uses the same docker environment as variant B which can be started in the same way, but requires a little more setup.

First, build the deployable file by right-clicking on the top-level `pom.xml` file and selecting `Run as... / Maven install`. This will start the build process and created a `zip` file in the `target`directory.

Next, got to the Demo AxonIvy Engine at http://localhost:8080/ and open the Engine Cockpit.

Create a new application with the exact name `anonymous-demos` and deploy the `zip` file which was generated before.

Open the link http://localhost/ to start the demo.

To work on internal tasks, use the `anonymous_user_approver` (Password `password`) owing the role `AnonymousUserApprover` for most tasks or the `anonymous_user_admin` (Password `password`) owning the role `AnonymousUserAdmin` for the Admin task.

To see e-mails generated by the AxonIvy Engine, open http://localhost:2580/ (no e-mails will be sent to the net).

### Open Project and Reverse Proxy Server Apache

The demos use an Apache Web Server as a Reverse Proxy. For security reasons, the Reverse Proxy will only forward requests to the `anonymous-demos-open` project (and certain needed accessories) to the internal AxonIvy Engine (or Designer). Therefore external users will not be able to see the *Portal* or other parts of any application.

Note: In these demos, the Apache Web Server will in fact implement two different reverse proxy servers, one forwarding request to http://localhost:81/ to the AxonIvy Designer and one forwarding reuqzests to http://localhost/ to the AxonIvy Engine.
<!--
The entries under the heading "Setup" are filled in this tab, e.g. for the Connector A-Trust here: https://market.axonivy.com/a-trust#tab-setup. 
-->

```
@variables.yaml@

External URL to application open space for building external links.
com.axonivy.demo.anonymous.baseUrl: http://localhost:81/designer/pro


```
