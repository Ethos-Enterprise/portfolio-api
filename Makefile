local-env-create:
	docker-compose -f stack.yaml up -d
	sleep 3
	docker cp data/ddl.sql postgres-portfolio:/var/lib/postgresql/data
	docker exec postgres-portfolio psql -h localhost -U admin -d postgres -a -f ./var/lib/postgresql/data/ddl.sql

local-env-destroy:
	docker-compose -f stack.yaml down