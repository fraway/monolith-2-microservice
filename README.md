GETTING STARTED
===============

First build all images with:

```bash
make images
```


Then deploy all objects to k8s with:

```bash
make deploy
```

Now add pdf-creator.io to the /etc/hosts file.


The app is reachable at http://pdf-creator.io.


1) Add a user
2) Create a PDF
3) Exec 
```bash microk8s.kubectl exec $(microk8s.kubectl get po -l name=pdf -o jsonpath='{.items[0].metadata.name}') -- ls /tmp ```
to see the pdf generated.