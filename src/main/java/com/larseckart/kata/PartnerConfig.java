package com.larseckart.kata;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("partner")
record PartnerConfig(String url) {

}
