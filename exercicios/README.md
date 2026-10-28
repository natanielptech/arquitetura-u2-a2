# Exercícios: qual padrão — ou nenhum?

**Unidade 2 · Aula 2 — Prof. Nataniel Paiva**

Para cada cenário, identifique primeiro o problema. Escolha um padrão somente se sua aplicabilidade e seus tradeoffs fizerem sentido.

## Cenários adicionais

Os cenários abaixo são exercícios criados para praticar os critérios da aula.
1. Um serviço calcula uma tarifa fixa, definida por lei, sem variantes previstas. A classe tem apenas uma regra.
2. O preço de entrega varia por modalidade; a aplicação recebe novas modalidades e o cliente não deve ganhar uma nova cadeia de condicionais a cada inclusão.
3. Um SDK de pagamento usa centavos como `long`, mas o domínio trabalha com `BigDecimal` e não deve depender do fornecedor.
4. Após um pedido ser confirmado, auditoria, notificação e análise reagem sem o caso de uso chamar diretamente cada consumidor.
5. Construir um relatório exige título obrigatório, vários campos opcionais e validação antes de criar o objeto.
6. Uma árvore de diretórios deve calcular o tamanho total tratando arquivo e pasta com a mesma operação.
7. Uma operação comum precisa reservar estoque, cobrar pagamento e agendar entrega; esse roteiro se repete em dois controladores.

<details>
<summary>Gabarito comentado</summary>

1. **Nenhum padrão.** Uma regra estável não justifica Strategy; manter a implementação direta evita abstração prematura.
2. **Strategy.** Existem algoritmos alternativos e crescimento previsto; valide também como a modalidade será selecionada.
3. **Adapter.** Traduz a interface externa para a abstração do domínio e reduz o acoplamento ao SDK.
4. **Observer.** Eventos permitem múltiplos consumidores sem acoplar o produtor a cada um. Defina observabilidade e tratamento de falhas.
5. **Builder.** A construção tem várias opções e invariantes; para um objeto pequeno, um construtor simples bastaria.
6. **Composite.** Folhas e grupos compartilham uma operação recursiva.
7. **Facade.** Uma interface focada pode encapsular a coordenação repetida do subsistema; evite transformar a fachada em um serviço com responsabilidades ilimitadas.

</details>
