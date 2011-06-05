package main

import "fmt"

func IFTemp0(condicao bool, verdadeiro int, falso int) (int) {
if condicao{
	 return verdadeiro
}
return falso
}
func idade() (int) {
return IFTemp0(!((true && true)),1,-(2.2 * 2))
}

func main() {
	fmt.Println(idade())
}