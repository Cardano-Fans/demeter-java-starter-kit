# demeter-java-starter-kit
Demeter template to start Cardano development with Java.

## This template will demononstrate a few basic Cardano functions such as
- accounts management via simple wallet
- transfering ADA to another wallet via simple transactions

We will be using one of the simplest (albeit centralised) methods which is via KOIOS API. By default cli app will use PREVIEW testnet.

## Account generation

Start application and invoke:
```shell
shell:>gen-new-account
Name      : Doug_Updegrave
Address   : addr_test1qra5hzp7q5wpx86vq34e6wts79hw86geet3ds2apuz5am7pqe4dmag83thfaqdwfgwrrk0duhwy92f6chgpr9937sktq2nk4cn
Mnemonics : hill october gesture tumble skull dice favorite leg lake tool perfect object hurdle asthma timber cotton street boring broken switch modify come ring crunch
```

List accounts:
```shell
shell:>list-accounts
Name      : Doug_Updegrave
Address   : addr_test1qra5hzp7q5wpx86vq34e6wts79hw86geet3ds2apuz5am7pqe4dmag83thfaqdwfgwrrk0duhwy92f6chgpr9937sktq2nk4cn
Mnemonics : hill october gesture tumble skull dice favorite leg lake tool perfect object hurdle asthma timber cotton street boring broken switch modify come ring crunch
```

Restore account:
```shell
shell:>restore-account -n "Rick_Shaw" -m "raw orient unknown junk various amused nest install sentence output funny slide concert panda wink job tackle dust exhaust embark mixture plug general mention"
Name      : Rick_Shaw
Address   : addr_test1qr0nrf2qchstgggv39da6unkwtarspe4tlrt6wc6sxupadpp2sa27xajzjt0twvst6c95pptefndu2xpfh8v6f55m0xqj93h9u
Mnemonics : raw orient unknown junk various amused nest install sentence output funny slide concert panda wink job tackle dust exhaust embark mixture plug general mention
```

Now let's load some balance on preview network to your test account. For this we will use free facet: https://docs.cardano.org/cardano-testnet/tools/faucet

Once faucet sends funds we should be able to see them via:
```shell
shell:>account-balance -n Rick_Shaw
Account's Rick_Shaw balance: 4 ADA
```
Alternatively if you don't have account configured you can just check balance for bech32 address:

```shell
shell:>address-balance -a addr_test1qrw5me8wf2s8rch2tc09ctmfspsrw53kjta7y7trjayl79r06gx4andcfwpfxa65kxhljsjgw3np9j779fqdg3u3kgpq3uhqew
Address's addr_test1qrw5me8wf2s8rch2tc09ctmfspsrw53kjta7y7trjayl79r06gx4andcfwpfxa65kxhljsjgw3np9j779fqdg3u3kgpq3uhqew balance: 9994 ADA
```

Now let's make a simple transactions from Rick to addr_test1qrw5me8wf2s8rch2tc09ctmfspsrw53kjta7y7trjayl79r06gx4andcfwpfxa65kxhljsjgw3np9j779fqdg3u3kgpq3uhqew

```shell
send-ada -f Rick_Shaw -t addr_test1qrw5me8wf2s8rch2tc09ctmfspsrw53kjta7y7trjayl79r06gx4andcfwpfxa65kxhljsjgw3np9j779fqdg3u3kgpq3uhqew -a 5
```
This should send 5 ADA from Rick_Shaw's account to addr_test1qrw5me8wf2s8rch2tc09ctmfspsrw53kjta7y7trjayl79r06gx4andcfwpfxa65kxhljsjgw3np9j779fqdg3u3kgpq3uhqew
```shell
Waiting for transaction to be processed ....
Waiting for transaction to be processed ....
...
Waiting for transaction to be processed ....
[Success] Transaction success, result: aa55e39ed2bb0776f7dc21a831634cc2cf9b6c7c996ed68e9caf1ab88c4f15bc
```
