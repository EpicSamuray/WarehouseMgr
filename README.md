# Warehouse Management

## Meine Definierten Anforderungen

**Ziel:**
Entwicklung einer Backend-Anwendung für die Lagerverwaltung
 
**Technische Anforderungen:**
 

* **API-Sprache:** GraphQL
* **Datenbank:** MongoDB
* **Messaging-System:** Kafka (Optional)
* **Backend-Framework:** Quarkus
* **Benutzerverwaltung:** Keycloak (Optional)

**Benutzerverwaltung:** Einfache Authentifizierung und Autorisierung (z. B. mit JWT) (Optional)
 

Das Vorhaben wird bei einem bestehenden Projekt angewendet. Das Backend, das verwendet wird, stammt aus dem Datenbank-Scripting-Kurs.

 

**Funktionalitäten**
* **GraphQL-API:**
    * Abrufen von Lagerbeständen
    * Verwalten von Produkten (Hinzufügen, Bearbeiten, Entfernen)
    * Bearbeiten von Bestellungen (Eingang, Warenausgang)
* **MongoDB:**
    * Speicherung von Produktinformationen
    * Speicherung von Bestelldaten
    * Speicherung von Lagerbewegungen
 

**Zusatzfunktionen:** (Optional)
 

**Benutzerverwaltung:** Einfache Authentifizierung und Autorisierung von Benutzern (z. B. mit JWT)
 

### Verwendetes Thema
### GraphQL in der Lagerverwaltung
* **Lagerbestände abzurufen:**
    * Abfragen des Lagerbestands für ein bestimmtes Produkt
    * Abfragen des Lagerbestands für alle Produkte
    * Abfragen des Lagerbestands an einem bestimmten Standort
* **Produkte zu verwalten:**
    * Hinzufügen neuer Produkte
    * Bearbeiten von vorhandenen Produkten
    * Entfernen von Produkten
* **Bestellungen zu bearbeiten:**
    * Erfassen von Bestellungen
    * Bearbeiten von Bestellungen
    * Verfolgen von Bestellungen (Optional)

## Überblick

Das Warehouse Management System ist ein modernes Lagerverwaltungstool, entwickelt mit Quarkus, um Effizienz und Schnelligkeit in Lageroperationen zu bringen. Es bietet eine umfassende GraphQL-API für eine flexible und interaktive Datenmanipulation, wodurch Anwender komplexe Abfragen und Mutationen mit Leichtigkeit durchführen können. Die Anwendung unterstützt Docker Compose, was die Bereitstellung und Skalierung vereinfacht, indem es die Anwendung und ihre Abhängigkeiten in Container verpackt. Durch die Nutzung moderner Technologien und Praktiken bietet das System eine robuste Lösung für die Verwaltung und Optimierung von Lagerbeständen und -operationen.

## Vorausetzung
* Docker 

## Docker Compose
Um die Bereitstellung und das Management des Warehouse Management Systems und seiner Abhängigkeiten zu vereinfachen, bieten wir eine Docker Compose Konfiguration an. Mit Docker Compose können Sie die Anwendung und die zugehörige MongoDB-Datenbank mit einem einzigen Befehl starten.

Stellen Sie sicher, dass Docker und Docker Compose auf Ihrem System installiert sind, und führen Sie dann den folgenden Befehl aus, um die Anwendung und die Datenbank zu starten:

```bash
docker compose up -d
```

## MongoDB dashboard
* Webseite: http://localhost:8081/
* Login 
    * **Username:** admin
    * **Password:** pass
## GraphQL-API
Das System bietet eine umfangreiche GraphQL-API, die es ermöglicht, Standorte, Produkte, Bestellungen und Benutzer effizient zu verwalten. Über GraphQL-Endpunkte können Sie komplexe Abfragen erstellen, um spezifische Daten zu erhalten, und Mutationen durchführen, um Ihre Daten zu aktualisieren.

Die GraphQL-API ist unter **http://localhost:8080/graphql** zugänglich. Sie können auch das GraphQL-UI unter **http://localhost:8080/q/graphql-ui/** besuchen, um Ihre Abfragen und Mutationen interaktiv zu testen.

### Beispiel-GraphQL-Abfrage:
```graphql
query{
  allProducts {
    name
    price
    category
  }
}
```
### Beispiel-GraphQL-Mutation:
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

### Beispiel-GraphQL-Subscription
```graphql
subscription{
  onStockMovement{
    id
    locationId
    quantity
  }
}
```

## Testen der API mit GraphQL-UI

Besuche nach dem Starten des Compose die Seite: **http://localhost:8080/q/graphql-ui**
Hier Kommst du zu denn Schemas da hast du alle möglichet typen und Methoden die du verwenden Kannst: **http://localhost:8080/graphql/schema.graphql**

