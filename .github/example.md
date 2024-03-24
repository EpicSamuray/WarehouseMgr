## GraphQL-API Beispiele

Hier sind ein Paar Beispiele um Zu starten...

In dieser Sektion finden Sie Beispiele für GraphQL-Mutationen, -Abfragen und -Subscriptions, die Sie im Warehouse Management System verwenden können.

### Queries

Hier sind Beispiele für die verschiedenen Abfragen, die Sie ausführen können:

#### Abfrage aller Produkte

```graphql
query {
  allProducts {
    id
    name
    price
    category
  }
}
```

#### Abfrage eines spezifischen Produkts nach ID

```graphql
query {
  productById(id: "Produkt-ID") {
    name
    price
    category
  }
}
```

#### Abfrage des Lagerbestands an einem bestimmten Standort

```graphql
query {
  locationById(id: "Standort-ID") {
    name
    currentCapacity
  }
}
```


### Mutationen

Hier sind Beispiele für die verschiedenen Mutationen, die Sie verwenden können:

#### Hinzufügen eines neuen Produkts

```graphql
mutation {
  createProduct(product: {
    name: "Neues Produkt",
    price: 19.99,
    category: "Hardware"
  }) {
    id
    name
  }
}
```

#### Aktualisieren eines vorhandenen Produkts

```graphql
mutation {
  updateProduct(id: "Produkt-ID", product: {
    name: "Aktualisiertes Produkt",
    price: 29.99,
    category: "Elektronik"
  }) {
    id
    name
  }
}
```

#### Entfernen eines Produkts

```graphql
mutation {
  deleteProduct(id: "Produkt-ID") {
    id
    name
  }
}
```

### Subscriptions

Hier ist ein Beispiel für eine Subscription:

#### Abonnieren von Lagerbewegungen

```graphql
subscription {
  onStockMovement {
    id
    locationId
    quantity
  }
}
```

Bitte stellen Sie sicher, dass Sie die korrekten IDs in Ihren Abfragen und Mutationen verwenden. Sie können diese Beispiele im GraphQL-UI testen, um ihre Funktionalität zu überprüfen.