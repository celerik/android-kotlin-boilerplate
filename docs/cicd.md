# Celerik CI/CD

## Description
Continuous integration and deployment is based on [Fastlane](https://fastlane.tools/), which allows to specify multiple sequences of tasks in a file named Fastfile. They can be executed in any computer without the need of an external platform.

## Available Lines
	- run_checks: 
		Params: None
		Description: It executes Kotlin linter, unitary tests and code coverage analysis from all project's modules.
		Command: `fastlane run_checks`