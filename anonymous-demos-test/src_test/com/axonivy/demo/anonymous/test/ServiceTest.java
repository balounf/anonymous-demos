package com.axonivy.demo.anonymous.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.axonivy.demo.anonymous.core.Service;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class ServiceTest {

  private static final String REGISTER_PROCESS_ID = "19C23640F9AD30D8";

  @Test
  void appRelativeLink_registerProcess() {
    String baseUrl = Ivy.var().get("com.axonivy.demo.anonymous.baseUrl");
    String result = Service.get().appRelativeLink(
        "anonymous-demos-open/" + REGISTER_PROCESS_ID + "/register.ivp");
    assertThat(result).isEqualTo(baseUrl + "/anonymous-demos-open/" + REGISTER_PROCESS_ID + "/register.ivp");
  }

  @Test
  void appRelativeLink_submitMissingDataWithToken() {
    String token = UUID.randomUUID().toString();
    String result = Service.get().appRelativeLink(
        "anonymous-demos-open/" + REGISTER_PROCESS_ID + "/submitMissingData.ivp?token=" + token);
    assertThat(result)
        .contains("/submitMissingData.ivp")
        .contains("token=" + token);
  }

  @Test
  void appRelativeLink_emailVerificationWithToken() {
    String token = UUID.randomUUID().toString();
    String result = Service.get().appRelativeLink(
        "anonymous-demos-open/" + REGISTER_PROCESS_ID + "/emailVerification.ivp?token=" + token);
    assertThat(result)
        .contains("/emailVerification.ivp")
        .contains("token=" + token);
  }

  @Test
  void appRelativeLink_normalizesRedundantPathSegments() {
    String direct = Service.get().appRelativeLink("anonymous-demos-open/start.ivp");
    String withDots = Service.get().appRelativeLink("anonymous-demos-open/sub/../start.ivp");
    assertThat(withDots).isEqualTo(direct);
  }

  @Test
  void singletonInstance() {
    assertThat(Service.get()).isSameAs(Service.get());
  }
}
