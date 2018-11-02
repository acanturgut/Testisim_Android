using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class App : MonoBehaviour 
{
	private GameObject go;

	private bool touching;

	private Vector2 start;
	private Vector2 end;
	
	private float deltaX;
	private float deltaY;

	private float swipeLeft;
	private float swipeRight;

	private float swipeUp;
	private float swipeDown;

	private float horizontalSwipe;
	private float verticalSwipe;

	private float coefHorizontal;
	private float coefVertical;

	private float coefWidth;
	private float coefHeight;

	private void Start () 
	{
		go = GameObject.Find("Whole");

		coefHorizontal = 0.5f;
		coefVertical = 0.4f;

		coefWidth = 0.01f;
		coefHeight = 0.0075f;

		swipeLeft = 0;
		swipeRight = 0;

		swipeUp = 0;
		swipeDown = 0;
	}
	
	
	private void Update () 
	{
		if (Input.GetMouseButtonDown(0))
		{
			touching = true;

			start = Input.mousePosition;
		}

		if (Input.GetMouseButtonUp(0))
		{
			touching = false;
		}

		if (touching)
		{
			end = Input.mousePosition;
			
			if (SwipingHorizontally()) 
				CalculateHorizontalSwipe();

			if (SwipingVertically())
				CalculateVerticalSwipe();

			Apply();
		}
	}

	private bool SwipingHorizontally()
	{
		float prevDeltaX = deltaX;
		float dragX = end.x - start.x;

		return (Screen.width * coefWidth) < Mathf.Abs(dragX - prevDeltaX);
	}

	private void CalculateHorizontalSwipe()
	{
		float prevDeltaX  = deltaX;
		deltaX = end.x - start.x;

		float diff = prevDeltaX - deltaX;

		if (0 < diff)
		{	
			swipeLeft += Mathf.Abs(diff) * coefHorizontal;
		}
		else
		{
			swipeRight += Mathf.Abs(diff) * coefHorizontal;
		}

		horizontalSwipe = swipeLeft - swipeRight;
		horizontalSwipe += 360;
		horizontalSwipe %= 360;
	}

	private bool SwipingVertically()
	{
		float prevDeltaY = deltaY;
		float dragY = end.y - start.y;

		return (Screen.height * coefHeight) < Mathf.Abs(dragY - prevDeltaY);
	}

	private void CalculateVerticalSwipe()
	{
		float prevDeltaY = deltaY;
		deltaY = end.y - start.y;

		float diff = prevDeltaY - deltaY;

		if (0 < diff)
		{	
			swipeDown += Mathf.Abs(diff) * coefVertical;
		}
		else
		{
			swipeUp += Mathf.Abs(diff) * coefVertical;
		}

		verticalSwipe = swipeUp - swipeDown;
		verticalSwipe += 360;
		verticalSwipe %= 360;
	}

	private void Apply()
	{
		Quaternion target = Quaternion.Euler(go.transform.localRotation.x + verticalSwipe, go.transform.localRotation.y + horizontalSwipe, 0);
		go.transform.localRotation = Quaternion.Lerp(go.transform.localRotation, target, 0.1f);
	}
}
