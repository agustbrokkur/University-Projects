using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class GameController : MonoBehaviour {
    public int waveNumber;
    public int waveNumberCounter;
    public int enemyNumber;
    public bool day;
	public AudioClip nightMusic;
	public AudioClip dayMusic;
	public AudioClip reposition;
	public AudioClip sel;
	public AudioClip attack;
	public AudioClip menuMusic;

	public Texture2D mouseReticle;

	public KeyCode Ready;
	public KeyCode Menu;
	public KeyCode NormalSpeed;
	public KeyCode DoubleSpeed;
	public KeyCode SonicSpeed;
	public KeyCode Research;
	public KeyCode AMoveKey;

    private static bool playingNow;
	private bool allDead;
    private bool gameOver = false;
    private bool gameWon = false;
    private Text towerHealthText;
    private Text upgradeSelected;
    private Text upgradeDiscription;
    private Text upgradeTextConfirm;
    private WaveSpawner spawner;
	private Corpses corpseCounter;
	private ManaController mana;
    private Health towerHealth;
    private GameObject upStatus;
    private GameObject todd;
    private GameObject minionHealthMax;
    private GameObject minionAttackMax;
    private GameObject minionSpeedMax;
    private GameObject waveAmount;
    private GameObject enemyAmount;
    private GameObject dayInterface;
    private GameObject nightInterface;
	private AudioSource audioSource;
	private AudioSource music;

    public bool bandbox = false;
	public int numSelected;
	private bool amove;
	private spells spellBook;
	private GameObject[] defenders;
	private Vector3 orgPos;
	private Vector3 currPos;
	private Rect rekt;
	private LineRenderer line;

    // upgrade attributes:
    [Header("Unit Health")]
    public float unitHealth = 100f;
    public float[] healthUpgrades = new float[4];
    [Header("Unit Damage")]
    public float unitDamage = 5f;
    public float[] damageUpgrades = new float[4];
    [Header("Unit Attack Speed")]
    public float unitAttackSpeed = 1f;
    public float[] attackSpeedUpgrades = new float[4];

    public int currentHPUpgrade;
    public int currentADUpgrade;
    public int currentAttackSpeedUpgrade;
    private int maxUpgrades;

    //Menus and misc
    [SerializeField]
    private GameObject mainMenuMenu;

    [SerializeField]
    private GameObject gameOptionMenu;

    [SerializeField]
    private GameObject gameOverMenu;

    [SerializeField]
    private GameObject winGameMenu;

    [SerializeField]
    private GameObject upgradeMenu;

    [SerializeField]
    private GameObject tower;

    //Buttons
    [SerializeField]
    private GameObject raiseGhoulButton;

    [SerializeField]
    private GameObject spookyboltButton;

    [SerializeField]
    private GameObject iceWallButton;

    [SerializeField]
    private GameObject placeGhoulButton;

    [SerializeField]
    private GameObject researchButton;

    [SerializeField]
    private GameObject readyButton;

    [SerializeField]
    private GameObject optionButton;

    [SerializeField]
    private GameObject normalSpeedButton;

    [SerializeField]
    private GameObject doubleSpeedButton;

    [SerializeField]
    private GameObject quadSpeedButton;

    void Awake() {
        Time.timeScale = 0;
        mainMenuMenu.SetActive(true);
        todd = GameObject.Find("Todd-Howard");
        waveAmount = GameObject.Find("Wave-Number-Amount");
        enemyAmount = GameObject.Find("Enemy-Number-Amount");
        minionHealthMax = GameObject.Find("Minion-Max-Health-Text");
        minionAttackMax = GameObject.Find("Minion-Attack-Damage-Text");
        minionSpeedMax = GameObject.Find("Minion-Attack-Speed-Text");
        dayInterface = GameObject.Find ("InterfaceDay");
		nightInterface = GameObject.Find ("InterfaceNight");
        upStatus = GameObject.Find("UpgradeStatus");
		nightInterface.SetActive (false);
        gameOverMenu.SetActive(false);
        winGameMenu.SetActive(false);
        upgradeMenu.SetActive(false);
		currentADUpgrade = 0;
		currentHPUpgrade = 0;
		currentAttackSpeedUpgrade = 0;
		maxUpgrades = 3;

        waveNumber = 1;
        waveNumberCounter = 0;
        waveAmount.GetComponent<Text>().text = waveNumber.ToString();

        enemyNumber = 0;
        enemyAmount.GetComponent<Text>().text = enemyNumber.ToString();
		amove = false;
		numSelected = 0;
    }

	// Use this for initialization
	void Start () {
		if (playingNow) {
			mainMenuMenu.SetActive (false);
			defaultSpeed ();
			spawner = GetComponentInChildren<WaveSpawner> ();
			corpseCounter = GetComponentInChildren<Corpses> ();
			spellBook = FindObjectOfType<spells> ();
			mana = GameObject.FindObjectOfType<ManaController> ();
	        towerHealth = tower.GetComponent<Health> ();
	        towerHealthText = GameObject.Find("TowerHealthText").GetComponent<Text>();
			audioSource = gameObject.AddComponent<AudioSource> ();
			music = gameObject.AddComponent<AudioSource> ();

	        //day = true;
			allDead = true;
			RenderSettings.fog = false;

			line = gameObject.AddComponent<LineRenderer> ();
			line.positionCount = 5;
			line.SetWidth(0.05f, 0.05f);
			line.useWorldSpace = true;
			Vector3[] zeroes = { Vector3.zero, Vector3.zero, Vector3.zero, Vector3.zero, Vector3.zero };
			line.SetPositions (zeroes);

	        waveNumberCounter = spawner.waveList.Count;
	        waveAmount.GetComponent<Text>().text = waveNumber.ToString() + " out of " + waveNumberCounter;

	        enemyNumber = spawner.waveList[waveNumber - 1].wave.Count;
	        enemyAmount.GetComponent<Text>().text = enemyNumber.ToString();

	        minionHealthMax.GetComponent<Text>().text = unitHealth.ToString();
	        minionAttackMax.GetComponent<Text>().text = unitDamage.ToString();
	        minionSpeedMax.GetComponent<Text>().text = unitAttackSpeed.ToString();

			music.clip = dayMusic;
			music.loop = true;
			music.Play ();
		} else {
			music = gameObject.AddComponent<AudioSource> ();

			music.clip = menuMusic;
			music.loop = true;
			music.Play ();
		}
    }
	
	// Update is called once per frame
	void Update () {
        if (day) {
            todd.GetComponent<Text>().text = "Day";
            towerHealthText.text = towerHealth.CurrentHealth.ToString() + "/" + towerHealth.maxHealth.ToString();
			if (towerHealth.CurrentHealth <= 0) {
				GameOverScreen ();
			}
			if (waveNumber <= waveNumberCounter && allDead) {
				Debug.Log ("spawning wave " + spawner.waveList.Count);
				spawner.prepAndSpawnWave (this, waveNumber - 1);
				allDead = false;
			}
        }
        else {
            todd.GetComponent<Text>().text = "Night";
        }
        if(waveNumber > waveNumberCounter) {
            GameWinningScreen();
        }
        if (Input.GetMouseButtonDown (1)) {
			Debug.Log ("RMouse , " + Input.GetMouseButtonDown (1));
		}
        changeTextIngame();
		mouseInputHandler ();
		handleInput ();
	}

    public void changeTextIngame() {
        waveAmount.GetComponent<Text>().text = waveNumber.ToString() + " Out of " + waveNumberCounter;
        enemyNumber = spawner.waveList[waveNumber - 1].wave.Count;
        enemyAmount.GetComponent<Text>().text = enemyNumber.ToString();
        minionHealthMax.GetComponent<Text>().text = healthUpgrades[currentHPUpgrade].ToString();
        minionAttackMax.GetComponent<Text>().text = damageUpgrades[currentADUpgrade].ToString();
        minionSpeedMax.GetComponent<Text>().text = attackSpeedUpgrades[currentAttackSpeedUpgrade].ToString();
    }

    public void handleInput() {
		if(Input.GetKeyDown(Research) && !day) {
            if(checkScreen()) {
                enterUpgradeMenu();
            }
		}
		if(Input.GetKeyDown(Ready) && !day) {
            if (checkScreen()) {
                beginDay();
            }
		}
		if(Input.GetKeyDown(NormalSpeed)) {
            if(checkScreen()) {
                defaultSpeed();
            }
		}
		if(Input.GetKeyDown(DoubleSpeed)) {
            if (checkScreen()) {
                doubleSpeed();
            }
		}
		if(Input.GetKeyDown(SonicSpeed)) {
            if (checkScreen()) {
                quadSpeed();
            }
        }
		if(Input.GetKeyDown(AMoveKey)) {
			if (numSelected > 0) {
				amove = true;
			}
		}
        if (Input.GetKeyDown(Menu)) {
            if (gameOptionMenu.activeSelf != true) {
                enterGameMenu();
            }
            else {
                exitGameMenu();
            }
        }
    }

	public void mouseInputHandler () {
		DefenderBehaviour[] def = FindObjectsOfType<DefenderBehaviour> ();
		if (Input.GetMouseButtonDown (0)) {
			RaycastHit hit;
			if (Physics.Raycast (Camera.main.ScreenPointToRay (Input.mousePosition), out hit, 100, 1 << 9)) {
				audioSource.PlayOneShot (sel);
				StartCoroutine (waitForMoveCommand (hit.transform.GetComponent<DefenderBehaviour> (), Vector3.zero, Vector3.zero));
			}
		}
		if (Input.GetMouseButtonDown (0) && !spellBook.placing && !amove) {
            RaycastHit hit;
			Physics.Raycast (Camera.main.ScreenPointToRay (Input.mousePosition), out hit, 100, 1 << 8);
			orgPos = hit.point;
            bandbox = true;
		} else if (Input.GetMouseButton (0) && bandbox) {
			RaycastHit hit;
			Physics.Raycast (Camera.main.ScreenPointToRay (Input.mousePosition), out hit, 100, 1 << 8);
			currPos = hit.point;
			currPos.y = 0;
			orgPos.y = 0;
			Vector3 p1 = new Vector3 (orgPos.x, 0, currPos.z);
			Vector3 p2 = new Vector3 (currPos.x, 0, orgPos.z);
			Vector3[] points = {orgPos, p1, currPos, p2, orgPos};
			line.SetPositions (points);
		} else if (Input.GetMouseButtonUp (0) && bandbox) {
            bandbox = false;
            RaycastHit hit;
			Physics.Raycast (Camera.main.ScreenPointToRay (Input.mousePosition), out hit);
			var xmax = Mathf.Max (orgPos.x, hit.point.x);
			var xmin = Mathf.Min (orgPos.x, hit.point.x);
			var ymax = Mathf.Max (orgPos.z, hit.point.z);
			var ymin = Mathf.Min (orgPos.z, hit.point.z);
			rekt = Rect.MinMaxRect (xmin, ymin, xmax, ymax);
			List<DefenderBehaviour> selectedDefs = new List<DefenderBehaviour> ();
			foreach (DefenderBehaviour defender in def) {
				if (rekt.Contains(new Vector2 (defender.transform.position.x, defender.transform.position.z))) {
					selectedDefs.Add (defender);
				}
			}
            if (selectedDefs.Count > 0) {
                selectedDefs[0].select ();
                selectedDefs[0].line.startWidth = 0.1f;
				StartCoroutine(waitForMoveCommand(selectedDefs[0], Vector3.zero, Vector3.zero));
				if (!audioSource.isPlaying) {
                    audioSource.PlayOneShot(sel);
                }
            }
            if(selectedDefs.Count > 1) {
                int angle = 90;
                float radius = 1.05f;
                for (int i = 1; i < selectedDefs.Count; i++) {
					if (i % 6 == 1 && i > 2) {
						angle += 30;
						radius += 0.51f;
					}
                    float x = Mathf.Sin(Mathf.Deg2Rad * angle) * radius;
                    float z = Mathf.Cos(Mathf.Deg2Rad * angle) * radius;
                    angle += 60;
                    Vector3 v = new Vector3(x,0,z);
                    Vector3 f = selectedDefs [i].transform.position - selectedDefs [0].transform.position;
                    StartCoroutine(waitForMoveCommand (selectedDefs [i], v, f));
			    }
            }
            Vector3[] zeroes = { Vector3.zero, Vector3.zero, Vector3.zero, Vector3.zero, Vector3.zero };
			line.SetPositions (zeroes);
			bandbox = false;
		}
	}

	public IEnumerator waitForMoveCommand (DefenderBehaviour p, Vector3 offset, Vector3 formation) {
		Debug.Log ("Reposition!");
        yield return null;
        p.select();
        numSelected++;
        while (true) {
			if (!spellBook.wasPlacing) {
				if (!amove) {
					if (Input.GetMouseButtonDown (1)) {
						RaycastHit hit;
						if (Physics.Raycast (Camera.main.ScreenPointToRay (Input.mousePosition), out hit, 100, 1 << 8 | 1 << 10)) {
							if (hit.transform.name == "Terrain") {
								if (!audioSource.isPlaying) {
									audioSource.PlayOneShot (reposition);
								}
								p.transform.GetComponent <DefenderBehaviour> ().goToPos (hit.point + offset);
							} else {
								if (!audioSource.isPlaying) {
									audioSource.PlayOneShot (attack);
								}
								p.enemy = hit.transform.GetComponentInParent<Collider> ();
							}
						}
					} else if (Input.GetMouseButtonDown (0)) {
						p.transform.GetComponent <DefenderBehaviour> ().deselect ();
						numSelected--;
						yield break;
					}
				} else {
					Cursor.SetCursor (mouseReticle, new Vector2 (32, 32), CursorMode.Auto);
					if (Input.GetMouseButtonDown (0)) {
						RaycastHit hit;
						if (Physics.Raycast (Camera.main.ScreenPointToRay (Input.mousePosition), out hit, 100, 1 << 8)) {
							if (!audioSource.isPlaying) {
								audioSource.PlayOneShot (attack);
							}
							StartCoroutine(p.transform.GetComponent <DefenderBehaviour> ().AttackMove (hit.point + offset));
						}
						yield return null;
						amove = false;
						Cursor.SetCursor (null, Vector2.zero, CursorMode.Auto);
					} else if (Input.GetMouseButtonDown (1)) {
                        yield return null;
                        amove = false;
                        Cursor.SetCursor(null, Vector2.zero, CursorMode.Auto);
                    }
				}
			}
			yield return null;
		}
	}

	public void endWave () {
		day = false;
		//Cursor.SetCursor (null, Vector2.zero, CursorMode.Auto);
		mana.resetMana ();
		Debug.Log ("Night falls...");
		dayInterface.gameObject.SetActive (false);
		nightInterface.gameObject.SetActive (true);
        //RenderSettings.fog = true;
        FindObjectOfType<Light>().intensity = 0.3f;
		allDead = true;
		var corpses = GameObject.FindGameObjectsWithTag ("Corpse");
		foreach (GameObject corpse in corpses) {
			corpseCounter.addCorpses (1);
			DestroyObject (corpse);
		}
		music.clip = nightMusic;
		music.loop = true;
		music.Play ();
        waveNumber++;
		spellBook.placing = false;
    }

	public void beginDay () {
		mana.resetMana ();
        defaultSpeed();
		day = true;
		Debug.Log ("Day breaks...");
		dayInterface.gameObject.SetActive (true);
		nightInterface.gameObject.SetActive (false);
        FindObjectOfType<Light>().intensity = 1f;
        //RenderSettings.fog = false;
        music.clip = dayMusic;
		music.loop = true;
		music.Play ();
	}

    public void enterUpgradeMenu()
    {
        Time.timeScale = 1;
        raiseGhoulButton.GetComponent<Button>().interactable = false;
        spookyboltButton.GetComponent<Button>().interactable = false;
        iceWallButton.GetComponent<Button>().interactable = false;
        placeGhoulButton.GetComponent<Button>().interactable = false;
        researchButton.GetComponent<Button>().interactable = false;
        readyButton.GetComponent<Button>().interactable = false;
        optionButton.GetComponent<Button>().interactable = false;
        normalSpeedButton.GetComponent<Button>().interactable = false;
        doubleSpeedButton.GetComponent<Button>().interactable = false;
        quadSpeedButton.GetComponent<Button>().interactable = false;
        upgradeMenu.SetActive(true);
    }

    public void exitUpgradeMenu()
    {
        Time.timeScale = 1;
        raiseGhoulButton.GetComponent<Button>().interactable = true;
        spookyboltButton.GetComponent<Button>().interactable = true;
        iceWallButton.GetComponent<Button>().interactable = true;
        placeGhoulButton.GetComponent<Button>().interactable = true;
        researchButton.GetComponent<Button>().interactable = true;
        readyButton.GetComponent<Button>().interactable = true;
        optionButton.GetComponent<Button>().interactable = true;
        normalSpeedButton.GetComponent<Button>().interactable = true;
        doubleSpeedButton.GetComponent<Button>().interactable = true;
        quadSpeedButton.GetComponent<Button>().interactable = true;
        upgradeMenu.SetActive(false);
    }

    public void enterGameMenu()
    {
        Time.timeScale = 0;
        raiseGhoulButton.GetComponent<Button>().interactable = false;
        spookyboltButton.GetComponent<Button>().interactable = false;
        iceWallButton.GetComponent<Button>().interactable = false;
        placeGhoulButton.GetComponent<Button>().interactable = false;
        researchButton.GetComponent<Button>().interactable = false;
        readyButton.GetComponent<Button>().interactable = false;
        optionButton.GetComponent<Button>().interactable = false;
        normalSpeedButton.GetComponent<Button>().interactable = false;
        doubleSpeedButton.GetComponent<Button>().interactable = false;
        quadSpeedButton.GetComponent<Button>().interactable = false;
        gameOptionMenu.SetActive(true);
    }

    public void exitGameMenu()
    {
        Time.timeScale = 1;
        raiseGhoulButton.GetComponent<Button>().interactable = true;
        spookyboltButton.GetComponent<Button>().interactable = true;
        iceWallButton.GetComponent<Button>().interactable = true;
        placeGhoulButton.GetComponent<Button>().interactable = true;
        researchButton.GetComponent<Button>().interactable = true;
        readyButton.GetComponent<Button>().interactable = true;
        optionButton.GetComponent<Button>().interactable = true;
        //normalSpeedButton.GetComponent<Button>().interactable = true;
        doubleSpeedButton.GetComponent<Button>().interactable = true;
        quadSpeedButton.GetComponent<Button>().interactable = true;
        gameOptionMenu.SetActive(false);
    }

    public void defaultSpeed () {
        if(Time.timeScale != 1) {
            normalSpeedButton.GetComponent<Button>().interactable = false;
            doubleSpeedButton.GetComponent<Button>().interactable = true;
            quadSpeedButton.GetComponent<Button>().interactable = true;
            Time.timeScale = 1;
        }
    }
   
	public void doubleSpeed () {
        if (Time.timeScale != 2) {
            doubleSpeedButton.GetComponent<Button>().interactable = false;
            normalSpeedButton.GetComponent<Button>().interactable = true;
            quadSpeedButton.GetComponent<Button>().interactable = true;
            Time.timeScale = 2;
        }
    }

    public void quadSpeed()
    {
        if (Time.timeScale != 4) {
            quadSpeedButton.GetComponent<Button>().interactable = false;
            normalSpeedButton.GetComponent<Button>().interactable = true;
            doubleSpeedButton.GetComponent<Button>().interactable = true;
            Time.timeScale = 4;
        }
    }

	public void upgradeHealth() {
		if (currentHPUpgrade < maxUpgrades) {
			if (corpseCounter.removeCorpses (3)) {
				Health[] units = FindObjectsOfType<Health> ();
				currentHPUpgrade++;
				foreach (Health unit in units) {
					if (unit.tag == "Defender") {
						unit.CurrentHealth += healthUpgrades [currentHPUpgrade] - unit.maxHealth;
						unit.maxHealth = healthUpgrades [currentHPUpgrade];
					}
				}
			} else { /*Indicate that there aren't enough corpses */ }
		} else { /*Indicate that you are already at max upgrades */ }
	}

	public void upgradeDamage() {
		if (currentADUpgrade < maxUpgrades) {
			if (corpseCounter.removeCorpses (3)) {
				DefenderBehaviour[] units = FindObjectsOfType<DefenderBehaviour> ();
				currentADUpgrade++;
				foreach (DefenderBehaviour unit in units) {
					unit.attackDamage = damageUpgrades [currentADUpgrade];
				}
			} else { /*Indicate that there aren't enough corpses */ }
		} else { /*Indicate that you are already at max upgrades */ }
	}

	public void upgradeAttackSpeed() {
		if (currentAttackSpeedUpgrade < maxUpgrades) {
			if (corpseCounter.removeCorpses (3)) {
				DefenderBehaviour[] units = FindObjectsOfType<DefenderBehaviour> ();
				currentAttackSpeedUpgrade++;
				foreach (DefenderBehaviour unit in units) {
					unit.attackSpeed = attackSpeedUpgrades [currentADUpgrade];
				}
			} else { /*Indicate that there aren't enough corpses */ }
		} else { /*Indicate that you are already at max upgrades */ }
	}
		
	public void upgradeUnit (GameObject unit) {
		DefenderBehaviour def = unit.GetComponent<DefenderBehaviour> ();
		Health hp = unit.GetComponent<Health> ();
		upgradeUnitDmg (def);
		upgradeUnitHealth (hp);
	}

	public void upgradeUnitDmg(DefenderBehaviour unit) {
		unit.attackDamage = damageUpgrades [currentADUpgrade];
		unit.attackSpeed = attackSpeedUpgrades [currentAttackSpeedUpgrade];
	}

	public void upgradeUnitHealth(Health unit) {
		unit.maxHealth = healthUpgrades [currentHPUpgrade];
	}

    private bool checkScreen() {
        if(mainMenuMenu.activeSelf == true || gameOptionMenu.activeSelf == true || gameOverMenu.activeSelf == true || upgradeMenu.activeSelf == true || winGameMenu.activeSelf == true) {
            return false;
        }
        return true;
    }

    void GameOverScreen()
    {
        if (!gameOver)
        {
            gameOver = true;
            gameOverMenu.SetActive(true);
            Time.timeScale = 0;
        }
    }

    void GameWinningScreen()
    {
        if (!gameWon)
        {
            gameWon = false;
            winGameMenu.SetActive(true);
            Time.timeScale = 0;
        }
    }

    public void Restart()
    {
        defaultSpeed();
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
        playingNow = true;
    }

    public void Quit()
    {
        Application.Quit();
    }
}
