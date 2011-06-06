package main

import "fmt"

type Debito struct{
	pontos int
	data *Data
	conta *Conta
	cartao *Cartao
	servico *Servico
}

type NodeDebito struct{
	valor *Debito
	next *NodeDebito
}
type ListDebito struct{
	head *NodeDebito
}

func (self *Debito) programa() (*ProgramaFidelidade) {
return new (ProgramaFidelidade)
}
func (self *Debito) getMulta(cor *TipoDeCor) (string) {
return ""
}

type NivelServico struct{
	nome string
	cor *TipoDeCor
	servicosDisponiveis *ListServico
	programafidelidade *ProgramaFidelidade
}

type NodeNivelServico struct{
	valor *NivelServico
	next *NodeNivelServico
}
type ListNivelServico struct{
	head *NodeNivelServico
}


type Socio struct{
	nivelDoServico *ListNivelServico
	conta *Conta
	cartao *Cartao
	programa *ListProgramaFidelidade
	socio *ListCliente
}

type NodeSocio struct{
	valor *Socio
	next *NodeSocio
}
type ListSocio struct{
	head *NodeSocio
}


type TipoDeCor struct{
}

type NodeTipoDeCor struct{
	valor *TipoDeCor
	next *NodeTipoDeCor
}
type ListTipoDeCor struct{
	head *NodeTipoDeCor
}


type Cliente struct{
	nome string
	pronomeTratamento string
	masculino bool
	dataAniversario *Data
	rg string
	cartoes *Cartao
	socio *Socio
}

type NodeCliente struct{
	valor *Cliente
	next *NodeCliente
}
type ListCliente struct{
	head *NodeCliente
}

func (self *Cliente) idade() (int) {
return 0
}

type Cartao struct{
	valido bool
	validoDesde *Data
	vencimento *Data
	cor *TipoDeCor
	nomeTratamentoCliente string
	proprietario *Cliente
	transacoes *ListTransacao
	socio *Socio
}

type NodeCartao struct{
	valor *Cartao
	next *NodeCartao
}
type ListCartao struct{
	head *NodeCartao
}


type Credito struct{
	torada int
	pontos int
	data *Data
	conta *Conta
	cartao *Cartao
	servico *Servico
}

type NodeCredito struct{
	valor *Credito
	next *NodeCredito
}
type ListCredito struct{
	head *NodeCredito
}

func (self *Credito) programa() (*ProgramaFidelidade) {
return new (ProgramaFidelidade)
}

type Servico struct{
	condicao bool
	pontosADebitar int
	pontosACreditar int
	descricao string
	parceiroprograma *ParceiroPrograma
	nivelservico *NivelServico
	transacoes *ListTransacao
}

type NodeServico struct{
	valor *Servico
	next *NodeServico
}
type ListServico struct{
	head *NodeServico
}


type Cheque struct{
	torada int
	pontos int
	data *Data
	conta *Conta
	cartao *Cartao
	servico *Servico
}

type NodeCheque struct{
	valor *Cheque
	next *NodeCheque
}
type ListCheque struct{
	head *NodeCheque
}

func (self *Cheque) programa() (*ProgramaFidelidade) {
return new (ProgramaFidelidade)
}
func (self *Cheque) getMulta(cor *TipoDeCor) (string) {
return ""
}

type ProgramaFidelidade struct{
	parceiros *ListParceiroPrograma
	nivelservicos *ListNivelServico
	socio *ListSocio
}

type NodeProgramaFidelidade struct{
	valor *ProgramaFidelidade
	next *NodeProgramaFidelidade
}
type ListProgramaFidelidade struct{
	head *NodeProgramaFidelidade
}

func (self *ProgramaFidelidade) obtemServicos() (*ListServico) {
return new (ListServico)
}
func (self *ProgramaFidelidade) cadastrar(c *Cliente) () {

}

type Data struct{
}

type NodeData struct{
	valor *Data
	next *NodeData
}
type ListData struct{
	head *NodeData
}


type Conta struct{
	pontos int
	transacoes *ListTransacao
	socio *Socio
}

type NodeConta struct{
	valor *Conta
	next *NodeConta
}
type ListConta struct{
	head *NodeConta
}

func (self *Conta) debitar(pts int) (bool) {
return (existsTemp0(self.transacoes) == true)
}
func (self *Conta) creditar(pts int) () {

}
func (self *Conta) estaVazia(nome string, lista *ListCartao) (bool) {
return false
}

type ParceiroPrograma struct{
	quantidadeDeClientes int
	programafidelidades *ListProgramaFidelidade
	servicosOferecidos *ListServico
}

type NodeParceiroPrograma struct{
	valor *ParceiroPrograma
	next *NodeParceiroPrograma
}
type ListParceiroPrograma struct{
	head *NodeParceiroPrograma
}


type Transacao struct{
	pontos int
	data *Data
	conta *Conta
	cartao *Cartao
	servico *Servico
}

type NodeTransacao struct{
	valor *Transacao
	next *NodeTransacao
}
type ListTransacao struct{
	head *NodeTransacao
}

func (self *Transacao) programa() (*ProgramaFidelidade) {
return new (ProgramaFidelidade)
}

func existsTemp0(lista *ListTransacao) (bool) {
atual := lista.head
for atual != nil {
	if((atual.valor.pontos > 10)){
		return true
	}
	atual = atual.next
}
return false
}

func main() {
	temp := new(Conta)
	fmt.Println(temp.debitar(0))
}