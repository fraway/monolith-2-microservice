build-monolithic:
	docker build -t monolithic:1.2 -f monolithic/Dockerfile monolithic
	docker save monolithic:1.2 > image.monolithic.tar
	microk8s ctr images import image.monolithic.tar

build-pdf:
	docker build -t pdf:1.2 -f pdf/Dockerfile pdf
	docker save pdf:1.2 > image.pdf.tar
	microk8s ctr images import image.pdf.tar

build-fe:
	docker build -t fe:1.2 -f frontend/Dockerfile frontend
	docker save fe:1.2 > image.fe.tar
	microk8s ctr images import image.fe.tar

images:
	# make build-monolithic
	make build-pdf
	# make build-fe	

	# clean up
	rm -f image.*.tar

deploy:
	microk8s.kubectl apply -f devops/