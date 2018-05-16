# User Data Magnolia Module

> This module is a POC for an initial approach to use Magnolia CMS as platform for managing the private user data as defined at the GDPR. It's build on top of the Public User Registration Module.
(https://documentation.magnolia-cms.com/display/DOCS56/Public+User+Registration+module)

## Features
> User data stored in new workspace. Users have the power to change the privacy level.

![Demo page with component](_dev/README-shareable.png)

![Component Dialog](_dev/README-shareable-dialog.png)

## Usage
Add this to your Page Template:

    userData:
        id: user-data-magnolia:components/userData
    
Add this to your project POM:

    <dependency>
      <groupId>elzahr.magnolia</groupId>
      <artifactId>user-data-magnolia</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>


## Demo
> [Provide how to view any included demonstration pages.]

To see a page demonstrating this component, open the Pages app in Magnolia AdminCentral and import the files in `_dev/demos`. (Import it directly at the root of the tree to see an example of the included css styling.)

## Possible improvements
* Add Content App to manage the data. Can be inspired in the existing Security App
* Integrate Facebook Login and insert all the data in the users-public-data workspace.
* Expose REST service with just the allowed data so third parties can integrate.

## Contribute to the Magnolia component ecosystem
It's easy to create components for Magnolia and share them on github and npm. I invite you to do so and join the community. Let's stop wasting time by developing the same thing again and again, rather let's help each other out by sharing our work and create a rich library of components.

Just add `magnolia-light-module` as a keyword to npm's package.json to make them easy to find and use on npm.

## License

MIT

## Contributors

Magnolia, https://magnolia-cms.com

Guillermo Ortiz, @elzahr

