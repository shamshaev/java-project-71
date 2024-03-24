# JSON and YML files comparator

[![Actions Status](https://github.com/shamshaev/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/shamshaev/java-project-71/actions)
[![Actions Status](https://github.com/shamshaev/java-project-71/actions/workflows/self-check.yml/badge.svg)](https://github.com/shamshaev/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/753b1554ef4c8dbefd86/maintainability)](https://codeclimate.com/github/shamshaev/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/753b1554ef4c8dbefd86/test_coverage)](https://codeclimate.com/github/shamshaev/java-project-71/test_coverage)

It's the command line interface application which compares 2 JSON or YML files and prints the difference in the format of your choice: stylish = default format, plain text or json.

## Example of comparing 2 files in stylish format
```bash
{
    chars1: [a, b, c]
  - chars2: [d, e, f]
  + chars2: false
  - checked: false
  + checked: true
  - default: null
  + default: [value1, value2]
  - id: 45
  + id: null
  - key1: value1
  + key2: value2
    numbers1: [1, 2, 3, 4]
  - numbers2: [2, 3, 4, 5]
  + numbers2: [22, 33, 44, 55]
  - numbers3: [3, 4, 5]
  + numbers4: [4, 5, 6]
  + obj1: {nestedKey=value, isNested=true}
  - setting1: Some value
  + setting1: Another value
  - setting2: 200
  + setting2: 300
  - setting3: true
  + setting3: none
}
```

## Installation and use
* JDK >= 20.0.1

```bash
# install
Make install

# help
./app/build/install/app/bin/app -h

# usage 
./app/build/install/app/bin/app [-f=format] filepath1 filepath2
# filepath1 filepath2: paths to the compared files. Path may be absolute or relative.
# -f: output format option. Available formats: stylish (default), plain or json.
```

<!--
https://asciinema.org/a/X12UUZEkNjCvVlyAw7A0aDQ9I  
https://asciinema.org/a/aCttTQ0aazfNixtt37ZDg0L40  
https://asciinema.org/a/P92BlwpjeENG1NDSQU8AlgwSW  
https://asciinema.org/a/i5BvGum8pddb7zbevVrI9lcg1  
https://asciinema.org/a/beWVAwSotTM7HushwunwGHmvt  
-->