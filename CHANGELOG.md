# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## Unreleased

## [4.0.0] - 2019-02-16
### Added
- Support for multiple versions
### Removed
- Sync capabilities (based on Firebase)

## [3.11.0] - 2019-02-16
### Added
- Support for new archetypes
### Changed
- Improve source filtering / configuration
### Fixed
- NullPointerException (seen on Android Vitals)

## [3.10.2] - 2019-02-09
### Fixed
- NullPointerException during upgrade because of inventory 

## [3.10.1] - 2019-02-08
### Fixed
- Exception for feats with empty category

## [3.10.0] - 2019-02-04
### Fixed
- Tooltip always displays attack modif even when not enabled (#22)
### Added
- New modifs for weapons: critical area and critical multiplier
- Feats and Features as cards (export PDF)

## [3.9.0] - 2019-02-01
### Added
- Spells as cards (export PDF)

## [3.8.5] - 2019-01-28
### Fixed
- NullPointer exception on spell filter

## [3.8.4] - 2019-01-12
### Changed
- Switch icon color for item context menu to match existing behaviour (modifs) 
- Pull request to support "bâton halfelin"

## [3.8.3] - 2019-01-07
### Fixed
- Invalid bonus modifications applied
- Missing adaptations for fat fingers and line height
- NullPointerException on PDF when race was not chosen

## [3.8.2] - 2019-01-05
### Fixed
- New tables (modifs & items) not properly created on a newly installed application

## [3.8.1] - 2019-01-05
### Fixed
- Drag-and-drop (order items) not working
- Clickable zone around FAB button

## [3.8.0] - 2019-01-05
### Changed
- Modifs & items: handled in separated tables
- Modifs can be linked to any item in inventory
- Icons of modifs linked to items are not displayed any more
- Items context menu
### Added
- New items fields: activated, category and location
- Floating Action Button (FAB) for adding a class, a modification or a item 

## [3.7.0] - 2019-12-29
### Added
- Character: add shortcut link to traits from classfeatures tab
- Character: add shortcut link to magic items from inventory

## [3.6.2] - 2019-11-16
### Fixed
- Bug: skill rank not considered in total

## [3.6.1] - 2019-11-16
### Fixed
- Crash during character import due to configuration loading (older devices)

## [3.6.0] - 2019-11-11
### Fixed
- PDF: Header is repeated for classfeatures (Rou 4: Rou4: ...)
- PDF: Linked talents is clipped
### Added
- Order inventory (drag-and-drop)
- New modifs for skills (all & based on an ability)
### Changed
- Feats list displays unlocked feats first
- Feat details improved (required & unlocked)
- Weapons display (same as in PDF)

## [3.5.0] - 2019-10-19
### Added
- Skill ranks per level (classes)
- Maximum skill ranks
- Weapons on character sheet
- Jump from character sheet to to catalog (equipment list)
- Character synchronization (for beta testing)

## [3.4.3] - 2019-09-03
### Changed
- Skills: #class skills and ranks
### Fixed
- NullPointerException during data update

## [3.4.2] - 2019-08-31
### Fixed
- Filters (lists) not working in contextual mode

## [3.4.1] - 2019-08-30
### Fixed
- Invalid BAB computation
### Changed
- Class feature list (sort by level)
### Added
- Jump from character sheet to catalog (for feats and class features)

## [3.4.0] - 2019-08-29
### Changed
- Class feature list (filter by class & archetype)
### Added
- Link a class feature with another
- Provide LinkTo description

## [3.3.0] - 2019-08-24
### Fixed
- Invalid BAB computation
### Changed
- Classfeature management for characters
- Ability to add/remove a class skill (only if not already provided)

## [3.2.5] - 2019-08-22
### Fixed
- Invalid BAB computation
### Added
- Class level for max spell level as modif

## [3.2.4] - 2019-08-20
### Fixed
- Crash on DB upgrade
- Crash on adding inventory items without specifying weight or value

## [3.2.3] - 2019-08-18
### Added
- Modif damages (close/ranged)

## [3.2.2] - 2019-08-18
### Changed
- "Generate PDF" button after the options

## [3.2.1] - 2019-08-17
### Fixed
- Missing PDF options (2nd page)
- Money (fields not on the same line)

## [3.2.0] - 2019-08-17
### Changed
- Chosen spells (per character)
### Added
- Traits (base, campaign, race, region, religion)
- Money (cp, sp, gp, pp)
- Global value
- PDF export of a character (2nd page)
- Experience

## [3.1.0] - 2019-08-09
### Added
- Filters for magic items
- 16 additonnal fields
- PDF export of a character (1st page)
### Changed
- When equipment is added to character, link to object is kept for future reference
- A modification can be linked to a weapon (attack bonuses only)
### Fixed
- Speed incorrectly showed in meters on character sheet overview

## [3.0.1] - 2019-07-28
### Fixed
- OutOfMemory during import (other than YML file)
- OutOfMemory due to DB cursor not closed

## [3.0.0] - 2019-07-21
### Added
- Class archetypes
- Magic items
- Treasure generator
### Changed
- Welcome page rebranded with icons
- Back goes back to welcome page
- Equipment merged (weapons, armors and others)
### Fixed
- Blank page on character sheet after pressing back
- Character list not updated when going back

## [2.12.0] - 2019-07-09
### Changed
- Add to character doesn't require pin when viewing details from character sheet
- New option for fat fingers (increase clickable UIs)
- Ability Score Calculator generates a "modif" for racial bonus
### Fixed
- Racial alternate traits not exported/imported

## [2.11.0] - 2019-07-09
### Added
- Racial alternate traits 

## [2.10.0] - 2019-07-07
### Added
- Preference for increasing line height (for lists) 
### Changed
- Improve Modifs to help fat fingers
### Fixed
- Character sheet not updated after changing hit points or speed 

## [2.9.0] - 2019-07-06
### Added
- Ability Score Calculator
### Fixed
- Top border non-visible (ex: hit points)

## [2.8.0] - 2019-05-02
### Added
- Display race traits on class features tab
- Filters for class features (traits & auto features)

## [2.7.0] - 2019-05-01
### Added
- Equipment
- Filters for equipment
### Fixed
- Carriage returns not properly handled in item detail (ex: feat - chair angélique)

## [2.6.2] - 2019-04-28
### Fixed
- Spells: casting time not displayed #15
- Cannot cancel "Inventory" popup if name and weight are not specified
- Character popup: crash on action after screen rotate

## [2.6.1] - 2019-04-25
### Fixed
- Invalid message when adding weapons

## [2.6.0] - 2019-04-23
### Added
- Conditions, weapons and armors 
- Character inventory
- Character summary mode

## [2.4.1] - 2019-04-09
### Fixed
- Display character's name in header #3
- Skill bonus not computed correctly #11
- Name disappear after screen rotation in item lists #9
- Quick action icons not visible when content is scrollable (long content) #6
- Two navigation items highlighted at the same time #7
- Description for races are incomplete #13
- Strange behaviour for pinned character #4

## [2.4.0] - 2019-03-24
### Added
- Support for multiple characters
- Support for exporting characters (as YML) and share it with others
- Support for importing characters (from YML)

## [2.3.0] - 2019-03-17
### Changed
- Class features (inventory, load data, sheet, ...)
- Data versions (data not loaded if version has not changed)
### Fixed
- Invalid message displayed on spell list for non-caster when different order is selected

## [2.2.0] - 2019-03-09
### Changed
- Speed optimizations for spells (indexed based on class, level, etc.)
- Only spells available based on class level are displayed
### Fixed
- Home screen statistics not updated
- Bug with Magicien vs Magus and similar

## [2.1.0] - 2019-02-10
### Added
- Modifications for characters: abilities, skills, combat values, etc.
### Changed
- Automatically show soft keyboard when search gets focus
### Fixed
- Bug with Bard spells being displayed for Barbarian
- Bug with search button which requires to click twice to work

## [2.0.0] - 2019-01-27
### Added
- Character sheet with: abilities, skills, feats, spells
- Ability, race and class pickers
- Skills, feats and spells filters
- Data management: races and classes
### Changed
- Actions on detail page now as small button on top of the page
- Feats can be added to current character

## [1.3.0] - 2019-01-16
### Added
- Support back to SDK 19 (Kitkat - Android 4.4)
### Fixed
- Up button in settings doesn't bring the user back to welcome screen (sdk <= 21)
- More/less button in item details view doesn't work (sdk <= 21)

## [1.2.1] - 2019-01-15 
### Fixed
- Spell filters don't work if only class and/or school selected (without level)
### Added
- Unit tests for util classes (96% code coverage)

## [1.2.0] - 2019-01-14
### Added
- Sources as preferences
- Source in item detail
- Source filtering for feats and spells
- Data migration for favorites
- Preference to disable disclaimer on welcome page
### Changed
- More information on welcome page
### Fixed
- Duplicated class (Rôd & Rod)

## [1.1.0] - 2019-01-12 
### Changed
- Number of items in title (header) on list page
### Added
- Filters for spells based on school, class and level
- Setting for showing long names in lists. List of spells shows then levels.
- Icons for items

## [1.0.0] - 2019-01-03
### Added
- Welcome page
- Navigation
- Data import from Yaml files (spells, skills, feats)
- List pages (spells, skills, feats) with search
- Detail page with toggle (metadata) and link to source (www.pathfinderfr.org)
- Favorites (add/remove, list)
- Preference menu with "Always show details" option
