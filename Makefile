## http://korea.gnu.org/manual/release/make/make-sjp/make-ko_toc.html
.PHONY : help clean test build run pull push stop-db
.DEFAULT : xxx

PROFILE ?= local
GIT_CURRENT_BRANCH := $(shell git rev-parse --abbrev-ref HEAD)

GRADLE_TASKS = clean test build

## https://gist.github.com/prwhite/8168133#gistcomment-3785627
help: ## show help message
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m\033[0m\n"} /^[$$()% 0-9a-zA-Z_-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

${GRADLE_TASKS}:
ifdef MODULE
	./gradlew :$(MODULE):$@
else
	./gradlew $@
endif

clean: ## gradle clean

test: clean ## gradle test

build: clean ## gradle build

run-server: java -jar zola-messaging-server/build/libs/zola-messaging-server-1.0.0.jar