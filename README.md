# Local Phone Number

[![Build Status](https://travis-ci.org/michaelruocco/local-phone-number.svg?branch=master)](https://travis-ci.org/michaelruocco/local-phone-number)
[![codecov](https://codecov.io/gh/michaelruocco/local-phone-number/branch/master/graph/badge.svg)](https://codecov.io/gh/michaelruocco/local-phone-number)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/447cdab4470148eda31695b1c5e8e957)](https://www.codacy.com/manual/michaelruocco/local-phone-number?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/local-phone-number&amp;utm_campaign=Badge_Grade)
[![BCH compliance](https://bettercodehub.com/edge/badge/michaelruocco/local-phone-number?branch=master)](https://bettercodehub.com/)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_local-phone-number&metric=alert_status)](https://sonarcloud.io/dashboard?id=michaelruocco_local-phone-number)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_local-phone-number&metric=sqale_index)](https://sonarcloud.io/dashboard?id=michaelruocco_local-phone-number)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_local-phone-number&metric=coverage)](https://sonarcloud.io/dashboard?id=michaelruocco_local-phone-number)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_local-phone-number&metric=ncloc)](https://sonarcloud.io/dashboard?id=michaelruocco_local-phone-number)

## Overview

This library supports a requirement which needed to be able to filter out telephone
numbers based on whether they were local to a given region. E.g. when executing in a UK/GB based
scenario, a list of phone numbers needed to be filtered to only include numbers which were valid
for GB regions including GB itself (United Kingdom), IM (Isle of man), JE (Jersey) and GG (Guernsey).

Under the covers the [Google phone number](https://github.com/google/libphonenumber) library does
the majority of the work. This library just provides the ability to be able to specify region configs
to aid deciding whether a number is local to a region or not.

It also defines a LocalPhoneNumber type which gives some other useful bits of functionality that it
also uses the Google phone number library for, e.g. formatting the provided raw phone number into a
standardised format, which can be configured, and determining whether a given number is a mobile number or
not, again this can be configured, e.g. for the US where in some scenarios the google library will return
FIXED_LINE_OR_MOBILE where a number can't be distinctly determined as a mobile, in this case the default
config will treat these a mobile numbers, but this can be modified base on requirements.

## Useful Commands

```gradle
// formats code, builds code, runs tests
./gradlew clean build
```

```gradle
// check that dependencies are up to date
./gradlew dependencyUpdates
```