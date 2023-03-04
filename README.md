# demeter-java-starter-kit
Demeter template to start Cardano development with Java.

## This template will demononstrate a few basic Cardano functions such as
- accounts generation
- transfering ADA to another wallet (simple transactions)
- invocation of Plutus V2 smart contract

We will be using one of the simplest (albeit centralised) methods which is via Blockfrost API (https://github.com/blockfrost)

In order to follow tutorial, you will have to create a blockfrost account on one of the test networks (testnet, preview and preprod).

## Account generation

Start application and invoke:
```
shell:>gen-new-account
Name      : Doug_Updegrave
Address   : addr_test1qra5hzp7q5wpx86vq34e6wts79hw86geet3ds2apuz5am7pqe4dmag83thfaqdwfgwrrk0duhwy92f6chgpr9937sktq2nk4cn
Mnemonics : hill october gesture tumble skull dice favorite leg lake tool perfect object hurdle asthma timber cotton street boring broken switch modify come ring crunch
```

List accounts:
```
shell:>list-accounts
Name      : Doug_Updegrave
Address   : addr_test1qra5hzp7q5wpx86vq34e6wts79hw86geet3ds2apuz5am7pqe4dmag83thfaqdwfgwrrk0duhwy92f6chgpr9937sktq2nk4cn
Mnemonics : hill october gesture tumble skull dice favorite leg lake tool perfect object hurdle asthma timber cotton street boring broken switch modify come ring crunch
```

## Faucet

TODO 

https://docs.cardano.org/cardano-testnet/tools/faucet
