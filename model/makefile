.PHONY: all run
	
all: build

build: codex-appetite-model

codex-appetite-model:
	docker build . -t $@

run:
	docker run -it \
		-u $(shell id -u):$(shell id - GID g) \
		-v $(shell pwd):/tf \
		-p 8080:8080 \
		--runtime nvidia \
		codex-appetite-model 
