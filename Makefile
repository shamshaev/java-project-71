clean:
	make -C app clean

build:
	make -C app build

run-dist:
	make -C app run-dist

test:
	make -C app test

lint:
	make -C app lint

report:
	make -C app report
